package com.example.exceptions;

public class FeatureNotSupportedByScreen extends Exception {
    public FeatureNotSupportedByScreen(String message) {
        super(message);
    }
}
