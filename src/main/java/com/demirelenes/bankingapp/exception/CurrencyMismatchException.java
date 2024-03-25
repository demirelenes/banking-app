package com.demirelenes.bankingapp.exception;

public class CurrencyMismatchException extends RuntimeException {

    public CurrencyMismatchException() {
        super("You can only exchange between TRY and one of the other valid exception types!");
    }

    public CurrencyMismatchException(String message) {
        super(message);
    }

    public CurrencyMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public CurrencyMismatchException(Throwable cause) {
        super(cause);
    }
}
