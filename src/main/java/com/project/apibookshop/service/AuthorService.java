package com.project.apibookshop.service;

import com.project.apibookshop.dto.AuthorDTO;
import com.project.apibookshop.mapper.Mapper;
import com.project.apibookshop.model.Author;
import com.project.apibookshop.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(Mapper::toDTOAuthor).toList();
    }

    @Override
    public AuthorDTO getAuthorById(Long id){
        return null;
    }

    @Override
    public AuthorDTO saveAuthor(AuthorDTO authorDTO){
        return null;
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO){
        return null;
    }

    @Override
    public void deleteAuthorById(Long id){
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author doesn't exist!");
        }
        authorRepository.deleteById(id);

    }

}
