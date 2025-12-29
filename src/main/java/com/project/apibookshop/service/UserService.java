package com.project.apibookshop.service;

import com.project.apibookshop.enums.UserRole;
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

}
