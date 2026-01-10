package com.project.apibookshop.service;

import com.project.apibookshop.dto.AuthRequestDTO;
import com.project.apibookshop.dto.AuthResponseDTO;
import com.project.apibookshop.dto.RegisterUserDTO;

public interface IAuthService {

    //SAVE USER WITH ECRYPTED PASSWORD
    String register(RegisterUserDTO registerUserDTO);

    //VALIDATE USER CREDENTIALS AND GENERATE JWT TOKEN
    AuthResponseDTO login(AuthRequestDTO authRequestDTO);

}
