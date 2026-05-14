package com.todotask.todo_task_api.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}
