package com.project.apibookshop.service;


import com.project.apibookshop.dto.AuthRequestDTO;
import com.project.apibookshop.dto.RegisterUserDTO;
import com.project.apibookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String register(RegisterUserDTO registerUserDTO){
        return null;
    }

    @Override
    public String login(AuthRequestDTO authRequestDTO){
        return null;
    }
}
