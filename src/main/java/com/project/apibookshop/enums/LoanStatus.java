package com.project.apibookshop.enums;

public enum LoanStatus {

    PENDING("Pending"),
    LOANED("Loaned"),
    FINISHED("Finished");

    private String status;

    LoanStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
