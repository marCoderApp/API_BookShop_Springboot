package com.project.apibookshop.service;


import com.project.apibookshop.dto.AuthRequestDTO;
import com.project.apibookshop.dto.AuthResponseDTO;
import com.project.apibookshop.dto.RegisterUserDTO;
import com.project.apibookshop.enums.UserRole;
import com.project.apibookshop.repository.UserRepository;
import com.project.apibookshop.security.JwtUtils;
import com.project.apibookshop.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.project.apibookshop.model.User;

import java.time.LocalDateTime;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    //REGISTER USER
    @Override
    public String register(RegisterUserDTO registerUserDTO){

        if (userRepository.findByEmail(registerUserDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists!");
        }

        User user = User.builder()
                .username(registerUserDTO.getUsername())
                .email(registerUserDTO.getEmail())
                .password(passwordEncoder.encode(registerUserDTO.getPassword()))
                .role(UserRole.READER) // Rol por defecto
                .registrationDate(LocalDateTime.now())
                .build();
        userRepository.save(user);
        return "Usuario registrado exitosamente";
    }

    //LOGIN USER
    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDTO.getEmail(),
                        authRequestDTO.getPassword()
                )
        );

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        return AuthResponseDTO.builder()
                .token(jwt)
                .id(userDetails.getId())
                .email(userDetails.getEmail())
                .role(role).build();
    }
}
