package com.fcv.citas.api.adapter.in.web;

import com.fcv.citas.api.adapter.in.web.dto.ApiError;
import com.fcv.citas.api.adapter.in.web.dto.ApiError.FieldError;
import com.fcv.citas.api.domain.exception.DuplicateDocumentException;
import com.fcv.citas.api.domain.exception.DuplicateEmailException;
import com.fcv.citas.api.domain.exception.InvalidCredentialsException;
import com.fcv.citas.api.domain.exception.InvalidRefreshTokenException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> new FieldError(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest().body(new ApiError(
                HttpStatus.BAD_REQUEST.value(), "Bad Request", "Datos de entrada inválidos", fieldErrors));
    }

    @ExceptionHandler({DuplicateEmailException.class, DuplicateDocumentException.class})
    public ResponseEntity<ApiError> handleConflict(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiError(
                HttpStatus.CONFLICT.value(), "Conflict", ex.getMessage(), null));
    }

    @ExceptionHandler({InvalidCredentialsException.class, InvalidRefreshTokenException.class})
    public ResponseEntity<ApiError> handleUnauthorized(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiError(
                HttpStatus.UNAUTHORIZED.value(), "Unauthorized", ex.getMessage(), null));
    }
}
