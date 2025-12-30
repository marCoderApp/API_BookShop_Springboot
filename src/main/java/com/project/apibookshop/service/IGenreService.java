package com.project.apibookshop.service;

import com.project.apibookshop.dto.GenreDTO;

import java.util.List;

public interface IGenreService {

    List<GenreDTO> getAllGenres();
    GenreDTO getGenreByName(String name);
    GenreDTO saveGenre(GenreDTO genreDTO);
}
