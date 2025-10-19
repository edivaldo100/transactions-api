package com.transactions;

public class TransactionValidationException extends RuntimeException {
    private final String code;

    public TransactionValidationException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

