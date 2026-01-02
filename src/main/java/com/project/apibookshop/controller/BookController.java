package com.project.apibookshop.controller;

import com.project.apibookshop.dto.BookDTO;
import com.project.apibookshop.enums.BookStatus;
import com.project.apibookshop.exception.NotFoundException;
import com.project.apibookshop.service.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;


    //GET ALL BOOKS
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    //SAVE BOOK
    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO){
        BookDTO created = bookService.saveBook(bookDTO);

        return ResponseEntity
                .created(URI.create("/api/books" + created.getId()))
                .body(created);

    }

    //SAVE MANY BOOKS
    @PostMapping("/batch")
    public ResponseEntity<List<BookDTO>> saveBooks(@RequestBody List<BookDTO> booksDTO){
        List<BookDTO> createdBooks = booksDTO.stream()
                .map(bookService::saveBook)
                .toList();
        return ResponseEntity.ok(createdBooks);
    }

    //GET BOOK BY ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    //UPDATE BOOK
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    //CHANGE BOOK STATUS
    @PatchMapping("/status/{status}/{id}")
    public ResponseEntity<String> changeBookStatusById(@PathVariable String status, @PathVariable Long id){
        try {
            // Convertimos el string a Enum manualmente para manejar el error
            BookStatus newStatus = BookStatus.valueOf(status.toUpperCase());
            bookService.changeBookStatusById(id, newStatus);
            return ResponseEntity.ok("Status updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid status. Use: AVAILABLE, LOANED, etc.");
        }
    }

    //DELETE BOOK
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book with ID: "+ id +" has been deleted!");
    }

    //GET BOOK BY TITLE
    @GetMapping("/title/{title}")
    public ResponseEntity<BookDTO> getBookByTitle(@PathVariable String title){
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    //GET BOOK BY AUTHOR FULL NAME
    @GetMapping("/fullname/{name}/{surname}")
    public ResponseEntity<List<BookDTO>> getBookByAuthorFullName(@PathVariable String name, @PathVariable String surname){

        List<BookDTO> books = bookService.getBookByAuthorFullName(name, surname);

        if(books.isEmpty()){
            throw new NotFoundException("Author not found!");
        }
        return ResponseEntity.ok(books);
    }

    //GET BOOK BY GENRE
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookDTO>> getBookByGenre(@PathVariable String genre){

        List<BookDTO> books = bookService.getBookByGenre_Name(genre);

        if(books.isEmpty()){
            throw new NotFoundException("Genre not found!");
        }

        return ResponseEntity.ok(books);
    }

}

