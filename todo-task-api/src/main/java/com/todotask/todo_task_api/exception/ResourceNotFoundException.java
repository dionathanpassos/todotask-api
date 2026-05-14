package com.todotask.todo_task_api.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resource) {
        super(resource);
    }
}
