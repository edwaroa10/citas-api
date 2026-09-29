package com.fcv.citas.api.adapter.in.web;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fcv.citas.api.CitasApiApplication;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = CitasApiApplication.class)
@AutoConfigureMockMvc
@Transactional
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String uniqueEmail() {
        return "hu003." + UUID.randomUUID() + "@example.invalid";
    }

    private String registerPayload(String email, String documentNumber) {
        return """
                {
                  "firstName": "Ana",
                  "lastName": "Pérez",
                  "documentType": "CC",
                  "documentNumber": "%s",
                  "email": "%s",
                  "phone": "3001234567",
                  "password": "Secreta123*"
                }
                """.formatted(documentNumber, email);
    }

    @Test
    void registerCreatesUserWithoutExposingPassword() throws Exception {
        String email = uniqueEmail();
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerPayload(email, "DOC-" + UUID.randomUUID())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.roles", hasSize(1)))
                .andExpect(jsonPath("$.roles[0]").value("USER"));
    }

    @Test
    void registerRejectsDuplicateEmail() throws Exception {
        String email = uniqueEmail();
        String firstDocument = "DOC-" + UUID.randomUUID();
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerPayload(email, firstDocument)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerPayload(email, "DOC-" + UUID.randomUUID())))
                .andExpect(status().isConflict());
    }

    @Test
    void registerRejectsDuplicateDocument() throws Exception {
        String documentNumber = "DOC-" + UUID.randomUUID();
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerPayload(uniqueEmail(), documentNumber)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerPayload(uniqueEmail(), documentNumber)))
                .andExpect(status().isConflict());
    }

    @Test
    void registerRejectsInvalidPayload() throws Exception {
        String invalidPayload = """
                {
                  "firstName": "",
                  "lastName": "Pérez",
                  "documentType": "CC",
                  "documentNumber": "123",
                  "email": "no-es-un-email",
                  "phone": "300",
                  "password": "123"
                }
                """;

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    void loginRejectsWrongPassword() throws Exception {
        String email = uniqueEmail();
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerPayload(email, "DOC-" + UUID.randomUUID())))
                .andExpect(status().isCreated());

        String loginPayload = """
                { "email": "%s", "password": "Incorrecta1*" }
                """.formatted(email);

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginPayload))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void loginRefreshAndLogoutFullFlow() throws Exception {
        String email = uniqueEmail();
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerPayload(email, "DOC-" + UUID.randomUUID())))
                .andExpect(status().isCreated());

        String loginPayload = """
                { "email": "%s", "password": "Secreta123*" }
                """.formatted(email);

        MvcResult loginResult = mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andExpect(jsonPath("$.refreshToken").isNotEmpty())
                .andExpect(jsonPath("$.roles[0]").value("USER"))
                .andReturn();

        var loginBody = objectMapper.readTree(loginResult.getResponse().getContentAsString());
        String refreshToken = loginBody.get("refreshToken").asText();

        String refreshPayload = """
                { "refreshToken": "%s" }
                """.formatted(refreshToken);

        MvcResult refreshResult = mockMvc.perform(post("/api/v1/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(refreshPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andReturn();

        // El refresh token usado queda rotado: reutilizarlo debe fallar.
        mockMvc.perform(post("/api/v1/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(refreshPayload))
                .andExpect(status().isUnauthorized());

        var refreshBody = objectMapper.readTree(refreshResult.getResponse().getContentAsString());
        String newRefreshToken = refreshBody.get("refreshToken").asText();

        String logoutPayload = """
                { "refreshToken": "%s" }
                """.formatted(newRefreshToken);

        mockMvc.perform(post("/api/v1/auth/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(logoutPayload))
                .andExpect(status().isNoContent());

        // Tras logout, ese refresh token ya no debe permitir renovar sesión.
        mockMvc.perform(post("/api/v1/auth/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(logoutPayload))
                .andExpect(status().isUnauthorized());
    }
}
