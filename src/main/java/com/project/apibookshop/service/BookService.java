package com.project.apibookshop.service;

import com.project.apibookshop.dto.BookDTO;
import com.project.apibookshop.exception.NotFoundException;
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
                .orElseThrow(() -> new RuntimeException("Genre not found!"));

        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .genre(genre)
                .rent_price(bookDTO.getRent_price())
                .purchase_price(bookDTO.getPurchase_price())
                .cover(bookDTO.getCover())
                .description(bookDTO.getDescription())
                .isbn(bookDTO.getIsbn())
                .publisher(bookDTO.getPublisher())
                .release_year(bookDTO.getRelease_year())
                .language(bookDTO.getLanguage())
                .pages(bookDTO.getPages())
                .edition(bookDTO.getEdition())
                .release_date(bookDTO.getRelease_date())
                .createdAt(bookDTO.getCreatedAt())
                .updatedAt(bookDTO.getUpdatedAt())
                .copies(bookDTO.getCopies())
                .build();

        return Mapper.toDTO(bookRepository.save(book));

    }

    @Override
    public BookDTO getBookById(Long id){

        return Mapper.toDTO(bookRepository.findById(id).orElseThrow(()->
                new NotFoundException("Book doesn't exist!")));
    }


    @Override
    public void deleteBookById(Long id){

        if(!bookRepository.existsById(id)){
            throw new NotFoundException("Book doesn't exist!");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO){

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book doesn't exist!"));

        book.setTitle(bookDTO.getTitle());
        book.setGenre(genreRepository.findByName(bookDTO.getGenre()).orElseThrow(() -> new RuntimeException("Genre not found!")));
        book.setRent_price(bookDTO.getRent_price());
        book.setPurchase_price(bookDTO.getPurchase_price());
        book.setCover(bookDTO.getCover());
        book.setDescription(bookDTO.getDescription());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublisher(bookDTO.getPublisher());
        book.setRelease_year(bookDTO.getRelease_year());
        book.setLanguage(bookDTO.getLanguage());
        book.setPages(bookDTO.getPages());
        book.setEdition(bookDTO.getEdition());
        book.setRelease_date(bookDTO.getRelease_date());
        book.setCreatedAt(bookDTO.getCreatedAt());
        book.setUpdatedAt(bookDTO.getUpdatedAt());
        book.setCopies(bookDTO.getCopies());

        return Mapper.toDTO(bookRepository.save(book));
    }

    @Override
    public BookDTO getBookByTitle(String title){
        return Mapper.toDTO(bookRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Book not found!")));
    }

    @Override
    public List<BookDTO> getBookByAuthorFullName(String authorName, String authorSurName){
        return bookRepository.findByAuthorNameAndSurname(authorName, authorSurName).stream().map(Mapper::toDTO).toList();
    }

    @Override
    public List<BookDTO> getBookByGenre(String genre){
        return bookRepository.findByGenre(genre).stream().map(Mapper::toDTO).toList();
    }
}
