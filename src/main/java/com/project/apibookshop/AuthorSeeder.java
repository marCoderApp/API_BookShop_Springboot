package com.project.apibookshop;

import com.project.apibookshop.model.Author;
import com.project.apibookshop.repository.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class AuthorSeeder implements CommandLineRunner {

    private final AuthorRepository authorRepository;

    public AuthorSeeder(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) {
        if (authorRepository.count() == 0) {
            Author author1 = new Author();
            author1.setName("Gabriel");
            author1.setSurname("García Márquez");
            author1.setNationality("Colombiano");
            author1.setBiography("Premio Nobel de Literatura en 1982.");
            author1.setBirthdate("1927-03-06");
            author1.setCountry("Colombia");
            author1.setCreatedAt(LocalDateTime.now());

            Author author2 = new Author();
            author2.setName("Isabel");
            author2.setSurname("Allende");
            author2.setNationality("Chilena");
            author2.setBiography("Escritora superventas de realismo mágico.");
            author2.setBirthdate("1942-08-02");
            author2.setCountry("Chile");
            author2.setCreatedAt(LocalDateTime.now());

            Author author3 = new Author();
            author3.setName("Jorge");
            author3.setSurname("Luis Borges");
            author3.setNationality("Argentino");
            author3.setBiography("Maestro del cuento fantástico y el ensayo.");
            author3.setBirthdate("1899-08-24");
            author3.setCountry("Argentina");
            author3.setCreatedAt(LocalDateTime.now());

            authorRepository.saveAll(Arrays.asList(author1, author2, author3));
            System.out.println(">>> AuthorSeeder: Autores iniciales creados.");
        }
    }
}