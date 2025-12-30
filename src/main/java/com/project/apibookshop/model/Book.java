package com.project.apibookshop.model;

import com.project.apibookshop.enums.BookStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

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
    private Integer copies;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    private BookStatus status;

}
