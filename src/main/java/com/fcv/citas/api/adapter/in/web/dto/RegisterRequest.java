package com.fcv.citas.api.adapter.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Size(max = 80) String firstName,
        @NotBlank @Size(max = 80) String lastName,
        @NotBlank @Size(max = 20) String documentType,
        @NotBlank @Size(max = 40) String documentNumber,
        @NotBlank @Email @Size(max = 160) String email,
        @NotBlank @Size(max = 30) String phone,
        @NotBlank @Size(min = 8, max = 100) String password) {
}
