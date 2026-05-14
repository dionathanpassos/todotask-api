package com.todotask.todo_task_api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todotask.todo_task_api.exception.ErrorResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{
    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                "Unauthorized",
                "Token inválido ou expirado",
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now(),
                null
        );

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(
                objectMapper.writeValueAsString(error)

        );

    }
}
