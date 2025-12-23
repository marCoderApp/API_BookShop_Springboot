package com.project.apibookshop.dto;

import com.project.apibookshop.enums.LoanStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Book_LoanDTO {

    private Long id;
    private Long book_id;
    private Long user_id;
    private String book_title;
    private Double rent_price;
    private Double purchase_price;
    private String start_date;
    private String end_date;
    private LoanStatus status;
}
