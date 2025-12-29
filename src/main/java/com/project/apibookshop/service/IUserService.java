package com.project.apibookshop.service;

public interface IUserService {

    void promoteUserToAdmin(Long id);
    void promoteUserToLibrarian(String email);

}
