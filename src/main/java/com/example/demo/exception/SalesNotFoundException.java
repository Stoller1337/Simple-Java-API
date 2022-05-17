package com.example.demo.exception;

public class SalesNotFoundException extends RuntimeException{
    public SalesNotFoundException(String message) {
        super(message);
    }
}
