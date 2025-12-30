package com.project.apibookshop.controller;

import com.project.apibookshop.dto.BookDTO;
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

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookDTO> saveBook(@RequestBody BookDTO bookDTO){
        BookDTO created = bookService.saveBook(bookDTO);

        return ResponseEntity
                .created(URI.create("/api/books" + created.getId()))
                .body(created);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
        return ResponseEntity.ok("Book with ID: "+ id +" has been deleted!");
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<BookDTO> getBookByTitle(@PathVariable String title){
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @GetMapping("/fullname/{name}-{surname}")
    public ResponseEntity<List<BookDTO>> getBookByAuthorFullName(@PathVariable String name, @PathVariable String surname){

        List<BookDTO> books = bookService.getBookByAuthorFullName(name, surname);

        if(books.isEmpty()){
            throw new NotFoundException("Author not found!");
        }
        return ResponseEntity.ok(books);
    }

}

