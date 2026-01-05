package com.project.apibookshop.controller;

import com.project.apibookshop.dto.AuthorDTO;
import com.project.apibookshop.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    //GET ALL AUTHORS
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors(){
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    //GET AUTHOR BY ID
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id){
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    //SAVE AUTHOR
    @PostMapping
    public ResponseEntity<AuthorDTO> saveAuthor(@RequestBody AuthorDTO authorDTO){
        return ResponseEntity.ok(authorService.saveAuthor(authorDTO));
    }

    //UPDATE AUTHOR
    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO){
        return ResponseEntity.ok(authorService.updateAuthor(id, authorDTO));
    }

    //DELETE AUTHOR BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthorById(@PathVariable Long id){
        authorService.deleteAuthorById(id);
        return ResponseEntity.ok("Author with ID: "+ id +" has been deleted!");
    }

}
