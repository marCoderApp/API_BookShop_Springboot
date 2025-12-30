package com.project.apibookshop.service;

import com.project.apibookshop.dto.UserDTO;
import com.project.apibookshop.enums.UserRole;
import com.project.apibookshop.mapper.Mapper;
import com.project.apibookshop.model.User;
import com.project.apibookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void promoteUserToAdmin(Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found!"));

            user.setRole(UserRole.ADMIN);

            userRepository.save(user);
    }

    @Override
    public void promoteUserToLibrarian(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        user.setRole(UserRole.LIBRARIAN);
        userRepository.save(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);

        return Mapper.toDTO(userRepository.save(user));

    }

}
