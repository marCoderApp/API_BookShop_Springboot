package com.project.apibookshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book_Author {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

}
