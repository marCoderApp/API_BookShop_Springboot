package com.project.apibookshop.service;

import com.project.apibookshop.dto.LoanDTO;

import java.util.List;

public interface ILoanService {

    List<LoanDTO> getAllLoans();
    List<LoanDTO> getLoansByUserId(Long userId);
    List<LoanDTO> getLoansByBookId(Long bookId);
    List<LoanDTO> getLoansByBookTitle(String title);
    List<LoanDTO> getLoansByBookAuthor(String author);
    List<LoanDTO> getLoansByBookGenre(String genre);
    List<LoanDTO> getLoansByStatus(String status);
    List<LoanDTO> getLoansByStartDate(String startDate);
    List<LoanDTO> getLoansByUserEmail(String email);
    LoanDTO saveLoan(LoanDTO loanDTO);
    LoanDTO getLoanById(Long id);
    LoanDTO updateLoan(Long id, LoanDTO loanDTO);
    void deleteLoanById(Long id);
}
