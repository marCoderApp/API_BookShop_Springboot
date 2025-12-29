package com.project.apibookshop.service;

import com.project.apibookshop.dto.BookDTO;
import com.project.apibookshop.mapper.Mapper;
import com.project.apibookshop.model.Book;
import com.project.apibookshop.model.Genre;
import com.project.apibookshop.repository.BookRepository;
import com.project.apibookshop.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    @Override
    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public BookDTO saveBook(BookDTO bookDTO){


        Genre genre = genreRepository.findByName(bookDTO.getGenre())
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));

        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .genre(genre)
                .rent_price(bookDTO.getRent_price())
                .purchase_price(bookDTO.getPurchase_price())
                .cover(bookDTO.getCover())
                .description(bookDTO.getDescription())
                .isbn(bookDTO.getIsbn())
                .publisher(bookDTO.getPublisher())
                .year(bookDTO.getYear())
                .language(bookDTO.getLanguage())
                .pages(bookDTO.getPages())
                .edition(bookDTO.getEdition())
                .release_date(bookDTO.getRelease_date())
                .createdAt(bookDTO.getCreatedAt())
                .updatedAt(bookDTO.getUpdatedAt())
                .build();

        return Mapper.toDTO(bookRepository.save(book));

    }

    @Override
    public BookDTO getBookById(Long id){
        return Mapper.toDTO(bookRepository.findById(id).orElseThrow());
    }


    @Override
    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO){
        return null;
    }


}
