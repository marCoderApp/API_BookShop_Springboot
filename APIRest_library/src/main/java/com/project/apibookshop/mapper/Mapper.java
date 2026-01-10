package com.project.apibookshop.mapper;

import com.project.apibookshop.dto.*;
import com.project.apibookshop.model.*;

public class Mapper {

    //BOOK TO DTO
    public static BookDTO toDTO(Book book){
        if(book == null){
            return null;
        }

        BookDTO dto = BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .genre(book.getGenre().getName())
                .rent_price(book.getRent_price())
                .purchase_price(book.getPurchase_price())
                .cover(book.getCover())
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .publisher(book.getPublisher())
                .release_year(book.getRelease_year())
                .language(book.getLanguage())
                .pages(book.getPages())
                .edition(book.getEdition())
                .release_date(book.getRelease_date())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .status(book.getStatus())
                .copies(book.getCopies())
                .build();

        if(book.getBookAuthors() != null && !book.getBookAuthors().isEmpty()){
            Author author = book.getBookAuthors().get(0).getAuthor();
            dto.setAuthorName(author.getName());
            dto.setAuthorSurname(author.getSurname());
        }

        return dto;
    }

    //GENRE TO DTO
    public static GenreDTO toDTO(Genre genre){
        if(genre == null){
            return null;
        }

        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

    //USER TO DTO
    public static UserDTO toDTO(User user){
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().getRole())
                .build();

    }

    //AUTHOR TO DTO
    public static AuthorDTO toDTOAuthor(Author author){
        if(author == null){
            return null;
        }

        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .biography(author.getBiography())
                .image(author.getImage())
                .nationality(author.getNationality())
                .birthdate(author.getBirthdate())
                .country(author.getCountry())
                .deathdate(author.getDeathdate())
                .gender(author.getGender())
                .country(author.getCountry())
                .createdAt(author.getCreatedAt())
                .updatedAt(author.getUpdatedAt())
                .build();
    }

    //LOAN TO DTO
    public static LoanDTO toDTOLoan(Loan loan){

        if(loan == null){
            return null;
        }

        return LoanDTO.builder()
                .id(loan.getId())
                .user_id(loan.getUser().getId())
                .book_id(loan.getBook_loans().getFirst().getBook().getId())
                .admin_id(loan.getAdmin().getId())
                .rent_price(loan.getRent_price())
                .purchase_price(loan.getPurchase_price())
                .total_price(loan.getTotal_price())
                .amount_books(loan.getAmount_books())
                .start_date(loan.getStartDate() != null ?
                         loan.getStartDate() : null)
                .end_date(loan.getEndDate())
                .status(loan.getStatus() != null ?
                        loan.getStatus().name() : null)
                .amount_books(loan.getAmount_books())
                .books_loansDTO(loan.getBook_loans().stream()
                        .map(Mapper::toBookLoanDTO).toList())
                .build();

    }

    //BOOK LOAN DTO

    public static Book_LoanDTO toBookLoanDTO(Book_Loan bookLoan){
       if (bookLoan == null) {
           return null;
       }

       return Book_LoanDTO.builder()
               .id(bookLoan.getId())
               .book_id(bookLoan.getBook().getId())
               .book_title(bookLoan.getBook().getTitle())
               .user_id(bookLoan.getLoan().getId())
               .rent_price(bookLoan.getRent_price())
               .purchase_price(bookLoan.getPurchase_price())
               .start_date(bookLoan.getLoan().getStartDate() != null ?
                       bookLoan.getLoan().getStartDate().toString() : null)
               .end_date(bookLoan.getLoan().getEndDate() != null ?
                       bookLoan.getLoan().getEndDate().toString() : null)
               .status(bookLoan.getLoan().getStatus())
               .build();
    }

}
