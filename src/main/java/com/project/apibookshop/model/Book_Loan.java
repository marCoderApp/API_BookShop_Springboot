package com.project.apibookshop.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book_Loan {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
    private Double rent_price;
    private Double purchase_price;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id")
    private Loan loan;



}
