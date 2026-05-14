package com.todotask.todo_task_api.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponseDTO(
        int status,
        String error,
        String message,
        String path,
        String method,
        LocalDateTime timestamp,
        Map<String, String> fieldErrors
) {
}

