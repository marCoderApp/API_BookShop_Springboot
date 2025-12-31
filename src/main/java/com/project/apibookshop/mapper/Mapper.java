package com.project.apibookshop.mapper;

import com.project.apibookshop.dto.AuthorDTO;
import com.project.apibookshop.dto.BookDTO;
import com.project.apibookshop.dto.GenreDTO;
import com.project.apibookshop.dto.UserDTO;
import com.project.apibookshop.model.Author;
import com.project.apibookshop.model.Book;
import com.project.apibookshop.model.Genre;
import com.project.apibookshop.model.User;

public class Mapper {

    public static BookDTO toDTO(Book book){
        if(book == null){
            return null;
        }

        return BookDTO.builder()
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
                .copies(book.getCopies())
                .build();
    }

    public static GenreDTO toDTO(Genre genre){
        if(genre == null){
            return null;
        }

        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

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

    public static AuthorDTO toDTOAuthor(Author author){
        if(author == null){
            return null;
        }

        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

}
