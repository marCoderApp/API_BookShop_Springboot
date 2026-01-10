package com.project.apibookshop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserDTO {

    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String role;

}
