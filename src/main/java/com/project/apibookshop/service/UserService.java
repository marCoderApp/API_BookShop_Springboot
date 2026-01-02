package com.project.apibookshop.service;

import com.project.apibookshop.dto.UserDTO;
import com.project.apibookshop.enums.UserRole;
import com.project.apibookshop.exception.NotFoundException;
import com.project.apibookshop.mapper.Mapper;
import com.project.apibookshop.model.User;
import com.project.apibookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    //PROMOTE USER TO ADMIN
    @Override
    public void promoteUserToAdmin(Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found!"));

            user.setRole(UserRole.ADMIN);

            userRepository.save(user);
    }

    //PROMOTE USER TO LIBRARIAN
    @Override
    public void promoteUserToLibrarian(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        user.setRole(UserRole.LIBRARIAN);
        userRepository.save(user);
    }

    //UPDATE USER
    @Override
    public UserDTO updateUser(UserDTO userDTO, String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);

        return Mapper.toDTO(userRepository.save(user));
    }

    //GET USER BY EMAIL
    @Override
    public UserDTO getUserByEmail(String email){
        return Mapper.toDTO(userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!")));
    }

    //GET USER BY ID
    @Override
    public UserDTO getUserById(Long id){
        return Mapper.toDTO(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!")));
    }

    //DELETE USER BY ID
    @Override
    public void deleteUserById(Long id){

        if(!userRepository.existsById(id)){
            throw new NotFoundException("Book doesn't exist!");
        }

        userRepository.deleteById(id);
    }

    //SAVE USER
    @Override
    public UserDTO saveUser(UserDTO userDTO){

        UserRole userRole;

        try{
                userRole = UserRole.valueOf(userDTO.getRole().toUpperCase());
        }catch (IllegalArgumentException | NullPointerException e){
                userRole = UserRole.READER;
        }

        User newUser = User.builder()
                .username(userDTO.getUsername())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .build();

       newUser.setRole(userRole);

       return Mapper.toDTO(userRepository.save(newUser));
    }


}
