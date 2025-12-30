package com.project.apibookshop.service;

import com.project.apibookshop.dto.GenreDTO;
import com.project.apibookshop.mapper.Mapper;
import com.project.apibookshop.model.Book;
import com.project.apibookshop.model.Genre;
import com.project.apibookshop.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements IGenreService{

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<GenreDTO> getAllGenres(){
        return genreRepository
                .findAll().stream().map(Mapper::toDTO)
                .toList();
    }

    @Override
    public GenreDTO getGenreByName(String name){
        return Mapper.toDTO(genreRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Genre not found!")));
    }

    @Override
    public GenreDTO saveGenre(GenreDTO genreDTO){
        Genre genre = Genre.builder().name(genreDTO.getName()).build();

        return Mapper.toDTO(genreRepository.save(genre));
    }
}
