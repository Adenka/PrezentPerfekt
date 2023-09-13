package com.gusia.backend.exceptions;

import java.util.*;

public class ObjectNotValidException extends RuntimeException {
    private final List<String> errorMessages;

    public ObjectNotValidException(List<String> errorMessages) {
        this.errorMessages = new ArrayList<>(errorMessages);
    }

    public List<String> getErrorMessages() {
        return new ArrayList<>(errorMessages);
    }
}
