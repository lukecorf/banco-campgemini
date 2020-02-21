package com.capgemini.banco.exception;

public class CustomException extends RuntimeException {
    public CustomException(Throwable throwable) {
        super(throwable);
    }

    public CustomException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public CustomException(String string) {
        super(string);
    }

    public CustomException() {
        super();
    }
}