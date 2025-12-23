package com.project.apibookshop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AuthorDTO {

    private Long id;
    private String name;
    private String surname;
    private String biography;
    private String image;
    private String nationality;
    private String birthdate;
    private String deathdate;
    private String gender;
    private String country;
    private LocalDateTime createdAt;



}
