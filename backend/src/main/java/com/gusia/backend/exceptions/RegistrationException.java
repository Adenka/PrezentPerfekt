package com.gusia.backend.exceptions;

public class RegistrationException extends RuntimeException {
    private final String message;

    public RegistrationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
