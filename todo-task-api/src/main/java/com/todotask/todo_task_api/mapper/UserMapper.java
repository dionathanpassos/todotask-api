package com.todotask.todo_task_api.mapper;

import com.todotask.todo_task_api.dto.user.UserRegisterRequestDTO;
import com.todotask.todo_task_api.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(UserRegisterRequestDTO requestDTO) {

        User user = new User();

        user.setName(requestDTO.name());
        user.setEmail(requestDTO.email());
        user.setPassword(passwordEncoder.encode(requestDTO.password()));

        return user;

    }
}
