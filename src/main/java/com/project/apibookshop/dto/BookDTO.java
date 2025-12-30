package com.project.apibookshop.dto;

import com.project.apibookshop.enums.BookStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {

    private Long id;
    private String title;
    private String genre;
    private Double rent_price;
    private Double purchase_price;
    private String cover;
    private String description;
    private String isbn;
    private String publisher;
    private String release_year;
    private String language;
    private String pages;
    private String edition;
    private LocalDateTime release_date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookStatus status;
    private Integer copies;
}
