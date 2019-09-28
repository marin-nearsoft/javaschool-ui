package com.javaschool.common;

public class QueueException extends RuntimeException {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
    public QueueException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
