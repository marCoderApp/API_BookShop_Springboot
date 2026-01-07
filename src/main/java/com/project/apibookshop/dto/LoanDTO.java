package com.project.apibookshop.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoanDTO {

    private Long id;
    private Long user_id;
    private Double rent_price;
    private Double purchase_price;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String status;
    private int amount_books;
    private List<Book_LoanDTO> books_loansDTO;
    private Double total_price;

}
