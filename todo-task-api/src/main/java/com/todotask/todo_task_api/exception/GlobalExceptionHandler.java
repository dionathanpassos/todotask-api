package com.todotask.todo_task_api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        HashMap<String, String> details = new HashMap<>();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ex.getBindingResult().getFieldErrors().forEach(error -> details.put(error.getField(), error.getDefaultMessage()));

        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                "Erro de validação",
                "Campos inválidos",
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now(),
                details

        );
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(
            BusinessException ex,
            HttpServletRequest request
    ) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                "Erro Regra de Negócio",
                ex.getMessage(),
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now(),
                null
        );

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCredentialNotFound(
            AuthenticationCredentialsNotFoundException ex,
            HttpServletRequest request
    ) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                "Não autorizado",
                ex.getMessage(),
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now(),
                null

        );

        return ResponseEntity.status(status).body(error);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getRequestURI(),
                request.getMethod(),
                LocalDateTime.now(),
                null
        );

        return ResponseEntity.status(status).body(error);
    }
}
