package com.project.apibookshop.repository;

import com.project.apibookshop.model.Book_Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookLoanRepository extends JpaRepository<Book_Loan, Long> {

    @Override
    Optional<Book_Loan> findById(Long aLong);
}
