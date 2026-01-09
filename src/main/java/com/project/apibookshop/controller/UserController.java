package com.project.apibookshop.controller;

import com.project.apibookshop.dto.UserDTO;
import com.project.apibookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //PROMOTE USER TO ADMIN
    @PatchMapping("/promote/{id}")
    public ResponseEntity<String> promoteUserToAdmin(@PathVariable String id) {
        userService.promoteUserToAdmin(Long.valueOf(id));
        return ResponseEntity.ok("User with ID: "+ id + " is now a Administrator");
    }

    //PROMOTE USER TO LIBRARIAN
    @PatchMapping("/promote/librarian/{email}")
    public ResponseEntity<String> promoteUserToLibrarian(@PathVariable String email) {
        userService.promoteUserToLibrarian(email);
        return ResponseEntity.ok("User with email: "+ email + " is now a Librarian");
    }

    //UPDATE PROFILE BY EMAIL
    @PatchMapping("/update/profile/{email}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String email, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(userDTO, email));
    }

    //SAVE USER
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    //GET USER BY EMAIL
    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    //GET USER BY ID
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    //DELETE USER BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User with ID: "+ id +" has been deleted!");
    }

}
