package com.project.apibookshop.service;

import com.project.apibookshop.dto.UserDTO;

public interface IUserService {

    void promoteUserToAdmin(Long id);
    void promoteUserToLibrarian(String email);
    UserDTO updateUser(UserDTO userDTO, String email);
    UserDTO saveUser(UserDTO userDTO);
    UserDTO getUserByEmail(String email);
    UserDTO getUserById(Long id);
    void deleteUserById(Long id);
}
