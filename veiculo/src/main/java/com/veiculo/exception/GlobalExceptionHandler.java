package com.veiculo.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<Object> body(HttpStatus status, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("timestamp", Instant.now().toString());
        map.put("status", status.value());
        map.put("error", status.getReasonPhrase());
        map.put("message", (message == null || message.isBlank()) ? defaultMessage(status) : message);
        return ResponseEntity.status(status).body(map);
    }

    private String defaultMessage(HttpStatus status) {
        return switch (status) {
            case NOT_FOUND -> "Recurso não encontrado";
            case BAD_REQUEST -> "Requisição inválida";
            case CONFLICT -> "Conflito de dados";
            default -> "Erro interno";
        };
    }
}
