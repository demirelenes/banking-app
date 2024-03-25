package com.demirelenes.bankingapp.exception;

public class InvalidCurrencyException extends RuntimeException {

    public InvalidCurrencyException() {
        super();
    }

    public InvalidCurrencyException(String message) {
        super(message);
    }

    public InvalidCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCurrencyException(Throwable cause) {
        super(cause);
    }
}
