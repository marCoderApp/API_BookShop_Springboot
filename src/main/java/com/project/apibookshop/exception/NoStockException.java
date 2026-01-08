package com.project.apibookshop.exception;

public class NoStockException extends RuntimeException {
    public NoStockException(String message) {
        super(message);
    }
}
