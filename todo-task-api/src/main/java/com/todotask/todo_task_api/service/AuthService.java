package com.todotask.todo_task_api.service;

import com.todotask.todo_task_api.dto.user.UserLoginRequestDTO;
import com.todotask.todo_task_api.dto.user.UserRegisterRequestDTO;
import com.todotask.todo_task_api.exception.BusinessException;
import com.todotask.todo_task_api.mapper.UserMapper;
import com.todotask.todo_task_api.model.User;
import com.todotask.todo_task_api.repository.UserRepository;
import com.todotask.todo_task_api.security.AuthResponseDTO;
import com.todotask.todo_task_api.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, UserMapper userMapper, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void register(UserRegisterRequestDTO requestDTO) {

        if (userRepository.existsByEmail(requestDTO.email())) {
            throw new BusinessException("Email em utilização");
        }

        User user = userMapper.toEntity(requestDTO);
        userRepository.save(user);
    }

    public AuthResponseDTO login(UserLoginRequestDTO requestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDTO.email(), requestDTO.password())
        );

        User user = (User) authentication.getPrincipal();

        return new AuthResponseDTO(jwtService.generateToken(user));
    }
}
