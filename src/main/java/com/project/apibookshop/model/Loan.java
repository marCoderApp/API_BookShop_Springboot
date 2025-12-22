package com.project.apibookshop.model;

import com.project.apibookshop.enums.LoanStatus;
import com.project.apibookshop.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated(jakarta.persistence.EnumType.STRING)
    private LoanStatus status;

    private Double total_price;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL,
    orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Book_Loan> book_loans = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false  )
    private User user;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

}
