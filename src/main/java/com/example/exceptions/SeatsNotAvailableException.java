package com.example.exceptions;

public class SeatsNotAvailableException extends Exception {
    public SeatsNotAvailableException(String message) {
        super(message);
    }
}
