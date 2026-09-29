package com.fcv.citas.api.adapter.in.web.dto;

import java.util.List;

public record ApiError(int status, String error, String message, List<FieldError> errors) {

    public record FieldError(String field, String message) {
    }
}
