package com.example.demo.exception;

public class ExpenseItemsNotFoundException extends RuntimeException{
    public ExpenseItemsNotFoundException(String message) {
        super(message);
    }
}
