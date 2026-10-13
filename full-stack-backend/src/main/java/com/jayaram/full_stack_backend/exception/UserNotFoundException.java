package com.jayaram.full_stack_backend.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not Found the user with id"+id);;
    }
}
