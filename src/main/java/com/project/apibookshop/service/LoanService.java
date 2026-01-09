package com.project.apibookshop.service;

import com.project.apibookshop.dto.Book_LoanDTO;
import com.project.apibookshop.dto.LoanDTO;
import com.project.apibookshop.enums.LoanStatus;
import com.project.apibookshop.exception.NoStockException;
import com.project.apibookshop.exception.NotFoundException;
import com.project.apibookshop.mapper.Mapper;
import com.project.apibookshop.model.Book;
import com.project.apibookshop.model.Book_Loan;
import com.project.apibookshop.model.Loan;
import com.project.apibookshop.model.User;
import com.project.apibookshop.repository.BookLoanRepository;
import com.project.apibookshop.repository.BookRepository;
import com.project.apibookshop.repository.LoanRepository;
import com.project.apibookshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService implements ILoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

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

        List<Loan> loans = loanRepository.findByBookId(id);
        return loans.stream().map(Mapper::toDTOLoan).toList();
    }

    //GET LOANS BY BOOK TITLE
    @Override
    public List<LoanDTO> getLoansByBookTitle(String title){

        List<Loan> loans = loanRepository.findByBookTitle(title);

        return loans.stream().map(Mapper::toDTOLoan).toList();
    }

    //GET LOANS BY BOOK AUTHOR
    @Override
    public List<LoanDTO> getLoansByBookAuthor(String author){

        List<Loan> loans = loanRepository.findByAuthorSurname(author);
        return loans.stream().map(Mapper::toDTOLoan).toList();
    }

    //GET LOANS BY BOOK GENRE
    @Override
    public List<LoanDTO> getLoansByBookGenre(String genre){

        List<Loan> loans = loanRepository.findByGenreName(genre);
        return loans.stream().map(Mapper::toDTOLoan).toList();
    }

    //GET LOANS BY STATUS
    @Override
    public List<LoanDTO> getLoansByStatus(String status){

        try{
            List<Loan> loans = loanRepository.findByStatus(
                    LoanStatus.valueOf(status.toUpperCase()));
            return loans.stream().map(Mapper::toDTOLoan).toList();
        }catch(IllegalArgumentException e){
            throw new RuntimeException("Loan status not found!");
        }

    }

    //GET LOANS BY START DATE
    @Override
    public List<LoanDTO> getLoansByStartDate(String startDate){

        try{
            List<Loan> loans = loanRepository.findByStartDate(startDate);
            return loans.stream().map(Mapper::toDTOLoan).toList();
        }catch(IllegalArgumentException e){
            throw new RuntimeException("Loan start date not found!");
        }
    }

    //GET LOANS BY USER EMAIL
    @Override
    public List<LoanDTO> getLoansByUserEmail(String email){

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User not found!")
        );

        try{
            List<Loan> loans = loanRepository.findByUser_Email(user.getEmail());
            return loans.stream().map(Mapper::toDTOLoan).toList();
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Loan user email not found!");
        }
    }

    //SAVE LOAN
    @Override
    @Transactional
    public LoanDTO saveLoan(LoanDTO loanDTO){

        User user = userRepository.findById(loanDTO.getUser_id()).orElseThrow(
                () -> new NotFoundException("User not found!")
        );

        User admin = userRepository.findById(loanDTO.getAdmin_id()).orElseThrow(
                ()-> new NotFoundException("Admin not found!")
        );

        Loan loan = Loan.builder()
                .user(user)
                .admin(admin)
                .startDate(LocalDateTime.now())
                .status(LoanStatus.LOANED)
                .total_price(0.0)
                .amount_books(0)
                .build();

        Loan loanSaved = loanRepository.save(loan);

        double totalAccumulated = 0.0;
        int bookCount = 0;
        List<Book_Loan> bookLoanList = new ArrayList<>();

        for(Book_LoanDTO bookLoanDTO : loanDTO.getBooks_loansDTO()){
            Book book = bookRepository.findById(bookLoanDTO.getBook_id()).orElseThrow(
                    ()-> new NotFoundException("Book not found!")
            );

            if(book.getCopies() <= 0){
                throw new NoStockException("Book is out of stock!");
            }

            book.setCopies(book.getCopies() - 1);

            Book_Loan bookLoan = Book_Loan.builder()
                    .loan(loanSaved)
                    .book(book)
                    .rent_price(book.getRent_price())
                    .purchase_price(book.getPurchase_price())
                    .build();

            bookLoanList.add(bookLoan);
            bookLoanRepository.save(bookLoan);
            bookRepository.save(book);
            totalAccumulated += book.getRent_price();
            bookCount++;
        }

        loanSaved.setTotal_price(totalAccumulated);
        loanSaved.setAmount_books(bookCount);
        loanSaved.setStatus(LoanStatus.LOANED);
        loanSaved.setBook_loans(bookLoanList);
        loanRepository.save(loanSaved);

        return Mapper.toDTOLoan(loanRepository.save(loanSaved));
    }

    //GET LOAN BY ID
    @Override
    public LoanDTO getLoanById(Long id){

        if (id == null){
            throw new RuntimeException("Loan ID is null!");
        }

        Loan loan = loanRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Loan not found!"));

        return Mapper.toDTOLoan(loan);
    }

    //UPDATE LOAN BY ID
    @Override
    public LoanDTO updateLoan(Long id, LoanDTO loanDTO){

        Loan loan = loanRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Loan not found!")
        );

       if(loanDTO.getRent_price() != null){
           loan.setRent_price(loanDTO.getRent_price());
       }

       if(loanDTO.getPurchase_price() != null){
           loan.setPurchase_price(loanDTO.getPurchase_price());
       }

       if(loanDTO.getStatus() != null){
           loan.setStatus(LoanStatus.valueOf(loanDTO.getStatus().toUpperCase()));
       }

       if(loanDTO.getTotal_price() != null){
           loan.setTotal_price(loanDTO.getTotal_price());
       }

       if (loanDTO.getStart_date() != null){
           loan.setStartDate(loanDTO.getStart_date());
       }

       if (loanDTO.getEnd_date() != null){
           loan.setEndDate(loanDTO.getEnd_date());
       }

       Loan savedLoan = loanRepository.save(loan);

        return Mapper.toDTOLoan(savedLoan);
    }

    //DELETE LOAN BY ID
    @Override
    public void deleteLoanById(Long id){

        Loan loan = loanRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Loan not found!")
        );

        loanRepository.delete(loan);
    }
}
