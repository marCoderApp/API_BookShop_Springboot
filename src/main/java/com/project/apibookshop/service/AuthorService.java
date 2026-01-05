package com.project.apibookshop.service;

import com.project.apibookshop.dto.AuthorDTO;
import com.project.apibookshop.exception.NotFoundException;
import com.project.apibookshop.mapper.Mapper;
import com.project.apibookshop.model.Author;
import com.project.apibookshop.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    //GET ALL AUTHORS
    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(Mapper::toDTOAuthor).toList();
    }

    //GET AUTHOR BY ID
    @Override
    public AuthorDTO getAuthorById(Long id){

        Author author = authorRepository.findById(id).orElseThrow(()-> new NotFoundException("Author not found!"));


        return Mapper.toDTOAuthor(author);
    }

    //SAVE AUTHOR
    @Override
    public AuthorDTO saveAuthor(AuthorDTO authorDTO){

        Author author = Author.builder()
                .name(authorDTO.getName())
                .surname(authorDTO.getSurname())
                .biography(authorDTO.getBiography())
                .image(authorDTO.getImage())
                .nationality(authorDTO.getNationality())
                .birthdate(authorDTO.getBirthdate())
                .deathdate(authorDTO.getDeathdate())
                .gender(authorDTO.getGender())
                .country(authorDTO.getCountry())
                .createdAt(LocalDateTime.now())
                .build();

        return Mapper.toDTOAuthor(authorRepository.save(author));
    }

    //UPDATE AUTHOR
    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO){

        Author author = authorRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Author not found!")
        );

        author.setName(authorDTO.getName());
        author.setSurname(authorDTO.getSurname());
        author.setBiography(authorDTO.getBiography());
        author.setImage(authorDTO.getImage());
        author.setNationality(authorDTO.getNationality());
        author.setBirthdate(authorDTO.getBirthdate());
        author.setDeathdate(authorDTO.getDeathdate());
        author.setGender(authorDTO.getGender());
        author.setCountry(authorDTO.getCountry());
        author.setUpdatedAt(LocalDateTime.now());

        return Mapper.toDTOAuthor(authorRepository.save(author));
    }

    //DELETE AUTHOR BY ID
    @Override
    public void deleteAuthorById(Long id){
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author doesn't exist!");
        }
        authorRepository.deleteById(id);

    }

}
