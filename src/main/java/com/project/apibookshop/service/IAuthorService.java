package com.project.apibookshop.service;


import com.project.apibookshop.dto.AuthorDTO;

import java.util.List;

public interface IAuthorService {

    List<AuthorDTO> getAllAuthors();
    AuthorDTO getAuthorById(Long id);
    AuthorDTO saveAuthor(AuthorDTO authorDTO);
    void deleteAuthorById(Long id);
    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);
}
