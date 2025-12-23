package com.project.apibookshop.enums;

public enum LoanStatus {

    PENDING("Pending"),
    FINISHED("Finished");

    private String status;

    LoanStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
