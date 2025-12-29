package com.project.apibookshop.enums;

public enum UserRole {

    ADMIN("Admin"),
    READER("Reader"),
    LIBRARIAN("Librarian");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
