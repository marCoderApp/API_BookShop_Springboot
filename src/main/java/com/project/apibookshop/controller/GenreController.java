package com.project.apibookshop.controller;

import com.project.apibookshop.dto.GenreDTO;
import com.project.apibookshop.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenres(){
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @PostMapping
    public ResponseEntity<GenreDTO> saveGenre(@RequestBody GenreDTO genreDTO){
        GenreDTO created = genreService.saveGenre(genreDTO);

        return ResponseEntity
                .created(URI.create("/api/genres" + created.getId()))
                .body(created);
    }

    @GetMapping("/{name}")
    public ResponseEntity<GenreDTO> getGenreByName(@PathVariable String name){
        return ResponseEntity.ok(genreService.getGenreByName(name));
    }

}
