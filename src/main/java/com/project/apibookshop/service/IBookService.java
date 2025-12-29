package com.project.apibookshop.service;

import com.project.apibookshop.dto.BookDTO;

import java.util.List;

public interface IBookService {

    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO saveBook(BookDTO bookDTO);
    void deleteBookById(Long id);
    BookDTO updateBook(Long id, BookDTO bookDTO);



}
