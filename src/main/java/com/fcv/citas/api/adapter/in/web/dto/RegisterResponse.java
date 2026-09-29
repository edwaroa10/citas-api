package com.fcv.citas.api.adapter.in.web.dto;

import java.util.Set;

public record RegisterResponse(Long id, String firstName, String lastName, String email, Set<String> roles) {
}
