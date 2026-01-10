package com.project.apibookshop.enums;

public enum BookStatus {

    AVAILABLE("Available"),
    LOANED("Loaned"),
    NOT_AVAILABLE("Not Available"),
    RETURNED("Returned");

    private String status;

    BookStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

}
