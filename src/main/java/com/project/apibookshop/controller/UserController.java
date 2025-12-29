package com.project.apibookshop.controller;

import com.project.apibookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PatchMapping("/promote/{id}")
    public ResponseEntity<String> promoteUserToAdmin(@PathVariable String id) {
        userService.promoteUserToAdmin(Long.valueOf(id));
        return ResponseEntity.ok("User with ID: "+ id + " is now a Administrator");
    }

    @PatchMapping("/promote/librarian/{email}")
    public ResponseEntity<String> promoteUserToLibrarian(@PathVariable String email) {
        userService.promoteUserToLibrarian(email);
        return ResponseEntity.ok("User with email: "+ email + " is now a Librarian");
    }
}
