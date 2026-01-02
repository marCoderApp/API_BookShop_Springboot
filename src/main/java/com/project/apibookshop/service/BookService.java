package com.project.apibookshop.service;

import com.project.apibookshop.dto.BookDTO;
import com.project.apibookshop.enums.BookStatus;
import com.project.apibookshop.exception.NotFoundException;
import com.project.apibookshop.mapper.Mapper;
import com.project.apibookshop.model.Author;
import com.project.apibookshop.model.Book;
import com.project.apibookshop.model.Book_Author;
import com.project.apibookshop.model.Genre;
import com.project.apibookshop.repository.AuthorRepository;
import com.project.apibookshop.repository.BookAuthorRepository;
import com.project.apibookshop.repository.BookRepository;
import com.project.apibookshop.repository.GenreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService implements IBookService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookAuthorRepository bookAuthorRepository;

    //GET ALL BOOKS
    @Override
    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream().map(Mapper::toDTO).toList();
    }

    //SAVE BOOK
    @Override
    public BookDTO saveBook(BookDTO bookDTO){

        Book book = Book.builder()
                .title(bookDTO.getTitle())
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

        if (book.getStatus() == null) book.setStatus(BookStatus.AVAILABLE);
        if (book.getCreatedAt() == null) book.setCreatedAt(LocalDateTime.now());

        Genre genre = genreRepository.findByName(bookDTO.getGenre())
                .orElseThrow(()-> new NotFoundException("Genre not found"));
        book.setGenre(genre);

        Book savedBook = bookRepository.save(book);

        Author author = authorRepository.findByNameAndSurname(bookDTO.getAuthorName(),
                bookDTO.getAuthorSurname()).
                orElseThrow(()-> new NotFoundException("Author not found"));

        Book_Author bookAuthor = new Book_Author();
        bookAuthor.setAuthor(author);
        bookAuthor.setBook(book);
        bookAuthorRepository.save(bookAuthor);

        savedBook.setBookAuthors(java.util.List.of(bookAuthor));

        return Mapper.toDTO(savedBook);

    }

    //GET BOOK BY ID
    @Override
    public BookDTO getBookById(Long id){

        return Mapper.toDTO(bookRepository.findById(id).orElseThrow(()->
                new NotFoundException("Book doesn't exist!")));
    }

    //DELETE BOOK BY ID
    @Override
    public void deleteBookById(Long id){

        if(!bookRepository.existsById(id)){
            throw new NotFoundException("Book doesn't exist!");
        }
        bookRepository.deleteById(id);
    }

    //UPDATE BOOK
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

    //GET BOOK BY TITLE
    @Override
    public BookDTO getBookByTitle(String title){
        return Mapper.toDTO(bookRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Book not found!")));
    }

    //GET BOOK BY AUTHOR FULL NAME
    @Override
    public List<BookDTO> getBookByAuthorFullName(String authorName, String authorSurName){
        return bookRepository.findByAuthorNameAndSurname(authorName, authorSurName).stream().map(Mapper::toDTO).toList();
    }

    //GET BOOK BY GENRE NAME
    @Override
    public List<BookDTO> getBookByGenre_Name(String genreName){
        try{
            List<Book> books = bookRepository.findByGenre_Name(genreName);
            return books.stream().map(Mapper::toDTO).toList();
        }catch(Exception e){
            throw new NotFoundException("Genre not found!");
        }
    }

    //CHANGE BOOK STATUS BY ID
    @Override
    public void changeBookStatusById(Long id, BookStatus status){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book doesn't exist!"));

        book.setStatus(status);
        book.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(book);
    }

    //CHANGE BOOK COPIES AMOUNT
    @Override
    public BookDTO changeBookCopies(Long id, Integer copies){
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Book doesn't exist"));

        book.setCopies(copies);
        book.setUpdatedAt(LocalDateTime.now());
        return Mapper.toDTO(bookRepository.save(book));
    }
}
