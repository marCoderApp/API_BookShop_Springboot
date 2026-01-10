package com.project.apibookshop.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookAuthorDTO {

    private Long id;
    private String bookTitle;
    private String authorName;
    private String authorSurname;
    private String genre;
    private Double rentPrice;
    private Double purchasePrice;
    private int yearRelease;
}
