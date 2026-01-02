package com.project.apibookshop.service;

import com.project.apibookshop.dto.BookDTO;
import com.project.apibookshop.enums.BookStatus;

import java.util.List;

public interface IBookService {

    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO saveBook(BookDTO bookDTO);
    void deleteBookById(Long id);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    BookDTO getBookByTitle(String title);
    List<BookDTO> getBookByAuthorFullName(String authorName, String authorSurname);
    List<BookDTO> getBookByGenre_Name(String genreName);
    void changeBookStatusById(Long id, BookStatus status);
}
