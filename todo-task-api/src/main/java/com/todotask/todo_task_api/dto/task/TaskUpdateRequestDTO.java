package com.todotask.todo_task_api.dto.task;

import jakarta.validation.constraints.NotBlank;

public record TaskUpdateRequestDTO(

        String title,

        String description


) {
}
