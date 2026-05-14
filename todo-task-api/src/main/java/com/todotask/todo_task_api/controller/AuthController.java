package com.todotask.todo_task_api.controller;

import com.todotask.todo_task_api.dto.user.UserLoginRequestDTO;
import com.todotask.todo_task_api.dto.user.UserRegisterRequestDTO;
import com.todotask.todo_task_api.security.AuthResponseDTO;
import com.todotask.todo_task_api.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UserRegisterRequestDTO requestDTO) {

        authService.register(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid UserLoginRequestDTO requestDTO) {
        AuthResponseDTO token = authService.login(requestDTO);

        return ResponseEntity.ok(token);
    }


}
