package com.project.apibookshop.repository;

import com.project.apibookshop.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUser_Id(Long userId);
    List<Loan> findByUser_Email(Long userId);
    List<Loan> findByBook_loans_Book_Id(Long bookId);
    List<Loan> findByBook_TitleContainingIgnoreCase(String title);
    List<Loan> findByStatus(String status);
    List<Loan> findByBook_Genre_NameContainingIgnoreCase(String genre);
    List<Loan> findByStartDate(String startDate);
    List<Loan> findByAuthor_NameContainingIgnoreCase(String author);
}
