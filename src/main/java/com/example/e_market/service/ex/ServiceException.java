package com.example.e_market.service.ex;

public class ServiceException extends RuntimeException {
    private final String message;

    public ServiceException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
