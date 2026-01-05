package com.project.apibookshop.service;

import com.project.apibookshop.dto.LoanDTO;
import com.project.apibookshop.mapper.Mapper;
import com.project.apibookshop.model.Loan;
import com.project.apibookshop.repository.BookLoanRepository;
import com.project.apibookshop.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService implements ILoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanService loanService;

    @Autowired
    private BookLoanRepository bookLoanRepository;

    //GET ALL LOANS
    @Override
    public List<LoanDTO> getAllLoans(){

        List<Loan> loans = loanRepository.findAll();
        List<LoanDTO> loansDTO = new ArrayList<>();
        LoanDTO loanDTO;

        for(Loan loan : loans){
            loanDTO = Mapper.toDTOLoan(loan);
            loansDTO.add(loanDTO);
        }

        return loansDTO;
    }

    //GET LOANS BY USER ID
    @Override
    public List<LoanDTO> getLoansByUserId(Long id){

        List<Loan> loans = loanRepository.findByUser_Id(id);
        List<LoanDTO> loansDTO = new ArrayList<>();
        LoanDTO loanDTO;

        for(Loan loan : loans){
            loanDTO = Mapper.toDTOLoan(loan);
            loansDTO.add(loanDTO);
        }

        return loansDTO;
    }

    //GET LOANS BY BOOK ID
    @Override
    public List<LoanDTO> getLoansByBookId(Long id){

        List<Loan> loans = loanRepository.findByBook_loans_Book_Id(id);
        return loans.stream().map(Mapper::toDTOLoan).toList();
    }

    //GET LOANS BY BOOK TITLE
    @Override
    public List<LoanDTO> getLoansByBookTitle(String title){

        List<Loan> loans = loanRepository.findByBook_TitleContainingIgnoreCase(title);

        return loans.stream().map(Mapper::toDTOLoan).toList();
    }
    

    //GET LOANS BY BOOK AUTHOR
    @Override
    public List<LoanDTO> getLoansByBookAuthor(String author){
        return null;
    }

    //GET LOANS BY BOOK GENRE
    @Override
    public List<LoanDTO> getLoansByBookGenre(String genre){
        return null;
    }

    //GET LOANS BY STATUS
    @Override
    public List<LoanDTO> getLoansByStatus(String status){
        return null;
    }

    //GET LOANS BY START DATE
    @Override
    public List<LoanDTO> getLoansByStartDate(String startDate){
        return null;
    }

    //GET LOANS BY USER EMAIL
    @Override
    public List<LoanDTO> getLoansByUserEmail(String email){
        return null;
    }

    //SAVE LOAN
    @Override
    public LoanDTO saveLoan(LoanDTO loanDTO){
        return null;
    }

    //GET LOAN BY ID
    @Override
    public LoanDTO getLoanById(Long id){
        return null;
    }

    //UPDATE LOAN BY ID
    @Override
    public LoanDTO updateLoan(Long id, LoanDTO loanDTO){
        return null;
    }

    //DELETE LOAN BY ID
    @Override
    public LoanDTO deleteLoanById(Long id){
        return null;
    }
}
