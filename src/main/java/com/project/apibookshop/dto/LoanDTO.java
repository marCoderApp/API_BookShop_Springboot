package com.project.apibookshop.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoanDTO {

    private Long id;
    private Long book_id;
    private Long user_id;
    private String book_title;
    private Double rent_price;
    private Double purchase_price;
    private String start_date;
    private String end_date;
    private String status;
    private List<Book_LoanDTO> books_loansDTO;
    private Double total_price;

}
