package com.project.apibookshop.controller;

import com.project.apibookshop.dto.AuthRequestDTO;
import com.project.apibookshop.dto.AuthResponseDTO;
import com.project.apibookshop.dto.RegisterUserDTO;
import com.project.apibookshop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO registerUserDTO){
        return ResponseEntity.ok(authService.register(registerUserDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO){
        return ResponseEntity.ok(authService.login(authRequestDTO));
    }
}
