package com.todotask.todo_task_api.dto.task;

import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        boolean completed,
        LocalDateTime createdAt,
        boolean active
) {
}
