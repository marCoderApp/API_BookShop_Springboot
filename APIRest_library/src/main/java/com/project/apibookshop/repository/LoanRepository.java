package com.project.apibookshop.repository;

import com.project.apibookshop.enums.LoanStatus;
import com.project.apibookshop.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUser_Id(Long userId);
    List<Loan> findByUser_Email(String email);
    @Query("SELECT DISTINCT l FROM Loan l JOIN l.book_loans bl WHERE bl.book.id = :bookId")
    List<Loan> findByBookId(@Param("bookId") Long bookId);

    @Query("SELECT DISTINCT l FROM Loan l JOIN l.book_loans bl WHERE LOWER(bl.book.title) LIKE LOWER(concat('%', :title, '%'))")
    List<Loan> findByBookTitle(@Param("title") String title);

    List<Loan> findByStatus(LoanStatus status);

    @Query("SELECT DISTINCT l FROM Loan l JOIN l.book_loans bl WHERE bl.book.genre.name = :genre")
    List<Loan> findByGenreName(@Param("genre") String genre);

    // Este método busca por fecha como String (asegúrate que el formato coincida en la DB)
    @Query("SELECT l FROM Loan l WHERE CAST(l.startDate AS string) LIKE concat(:startDate, '%')")
    List<Loan> findByStartDate(@Param("startDate") String startDate);

    @Query("SELECT DISTINCT l FROM Loan l JOIN l.book_loans bl JOIN bl.book.bookAuthors ba WHERE LOWER(ba.author.surname) LIKE LOWER(concat('%', :author, '%'))")
    List<Loan> findByAuthorSurname(@Param("author") String author);
}
