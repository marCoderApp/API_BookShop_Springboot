package com.project.apibookshop.repository;

import com.project.apibookshop.enums.LoanStatus;
import com.project.apibookshop.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUser_Id(Long userId);
    List<Loan> findByUser_Email(String email);
    List<Loan> findByBook_loans_Book_Id(Long bookId);
    List<Loan> findByBook_loans_Book_TitleContainingIgnoreCase(String title);
    List<Loan> findByStatus(LoanStatus status);
    List<Loan> findByBook_Book_Genre_Name(String genre);
    List<Loan> findByStartDate(String startDate);
    List<Loan> findByBook_loans_Book_Authors_Author_SurnameContainingIgnoreCase(String author);
}
