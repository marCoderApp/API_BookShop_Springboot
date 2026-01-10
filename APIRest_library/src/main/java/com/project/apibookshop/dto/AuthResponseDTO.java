package com.project.apibookshop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {

    private Long id;
    private String token;
    private String email;
    private String role;
}
