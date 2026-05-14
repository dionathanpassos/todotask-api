package com.todotask.todo_task_api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequestDTO(

        String name,

        @NotBlank(message = "O Email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,


        @NotBlank(message = "A senha é obrigatoria")
        String password
) {
}
