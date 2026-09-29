package com.fcv.citas.api.adapter.in.web;

import com.fcv.citas.api.adapter.in.web.dto.LoginRequest;
import com.fcv.citas.api.adapter.in.web.dto.LogoutRequest;
import com.fcv.citas.api.adapter.in.web.dto.RefreshRequest;
import com.fcv.citas.api.adapter.in.web.dto.RegisterRequest;
import com.fcv.citas.api.adapter.in.web.dto.RegisterResponse;
import com.fcv.citas.api.adapter.in.web.dto.TokenResponse;
import com.fcv.citas.api.application.port.in.LoginUseCase;
import com.fcv.citas.api.application.port.in.LoginUseCase.LoginCommand;
import com.fcv.citas.api.application.port.in.LogoutUseCase;
import com.fcv.citas.api.application.port.in.RefreshSessionUseCase;
import com.fcv.citas.api.application.port.in.RegisterUserUseCase;
import com.fcv.citas.api.application.port.in.RegisterUserUseCase.RegisterUserCommand;
import com.fcv.citas.api.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;
    private final RefreshSessionUseCase refreshSessionUseCase;
    private final LogoutUseCase logoutUseCase;

    public AuthController(
            RegisterUserUseCase registerUserUseCase,
            LoginUseCase loginUseCase,
            RefreshSessionUseCase refreshSessionUseCase,
            LogoutUseCase logoutUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUseCase = loginUseCase;
        this.refreshSessionUseCase = refreshSessionUseCase;
        this.logoutUseCase = logoutUseCase;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest request) {
        User user = registerUserUseCase.register(new RegisterUserCommand(
                request.firstName(),
                request.lastName(),
                request.documentType(),
                request.documentNumber(),
                request.email(),
                request.phone(),
                request.password()));
        return new RegisterResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getRoles());
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {
        return TokenResponse.from(loginUseCase.login(new LoginCommand(request.email(), request.password())));
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(@Valid @RequestBody RefreshRequest request) {
        return TokenResponse.from(refreshSessionUseCase.refresh(request.refreshToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody LogoutRequest request) {
        logoutUseCase.logout(request.refreshToken());
        return ResponseEntity.noContent().build();
    }
}
