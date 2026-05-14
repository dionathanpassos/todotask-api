package com.todotask.todo_task_api.dto.task;

import jakarta.validation.constraints.NotBlank;

public record TaskRequestDTO(

        @NotBlank(message = "Título é obrigatório")
        String title,

        String description


) {
}
