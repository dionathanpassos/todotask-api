package com.todotask.todo_task_api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todotask.todo_task_api.exception.ErrorResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDanieHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public CustomAccessDanieHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {

        HttpStatus status = HttpStatus.FORBIDDEN;

        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                "Forbidden",
                "Acesso negado",
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now(),
                null
        );

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(
                objectMapper.writeValueAsString(error)

        );

    }
}
