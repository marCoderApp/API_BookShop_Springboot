package com.project.apibookshop.mapper;

import com.project.apibookshop.dto.BookDTO;
import com.project.apibookshop.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

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
                .year(book.getYear())
                .language(book.getLanguage())
                .pages(book.getPages())
                .edition(book.getEdition())
                .release_date(book.getRelease_date())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

}
