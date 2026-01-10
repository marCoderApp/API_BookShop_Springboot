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
            Author tolkien = new Author();
            tolkien.setName("J.R.R.");
            tolkien.setSurname("Tolkien");
            tolkien.setNationality("Británico");
            tolkien.setBiography("Padre de la literatura de fantasía moderna y autor de El Señor de los Anillos.");
            tolkien.setBirthdate("1892-01-03");
            tolkien.setCountry("Reino Unido");
            tolkien.setCreatedAt(LocalDateTime.now());
            authorRepository.save(tolkien);

            // 2. George Orwell
            Author orwell = new Author();
            orwell.setName("George");
            orwell.setSurname("Orwell");
            orwell.setNationality("Británico");
            orwell.setBiography("Famoso por sus novelas distópicas como 1984 y Rebelión en la granja.");
            orwell.setBirthdate("1903-06-25");
            orwell.setCountry("Reino Unido");
            orwell.setCreatedAt(LocalDateTime.now());
            authorRepository.save(orwell);

            // 3. Robert C. Martin (Autor de Clean Code)
            Author uncleBob = new Author();
            uncleBob.setName("Robert");
            uncleBob.setSurname("Martin");
            uncleBob.setNationality("Estadounidense");
            uncleBob.setBiography("Conocido como 'Uncle Bob', es uno de los padres del Manifiesto Ágil y experto en Clean Code.");
            uncleBob.setBirthdate("1952-12-05");
            uncleBob.setCountry("USA");
            uncleBob.setCreatedAt(LocalDateTime.now());
            authorRepository.save(uncleBob);

            // 4. Stephen King
            Author king = new Author();
            king.setName("Stephen");
            king.setSurname("King");
            king.setNationality("Estadounidense");
            king.setBiography("El maestro del terror contemporáneo, autor de It y El Resplandor.");
            king.setBirthdate("1947-09-21");
            king.setCountry("USA");
            king.setCreatedAt(LocalDateTime.now());
            authorRepository.save(king);

            // 5. Gabriel García Márquez (Para completar tu lista inicial)
            Author gabo = new Author();
            gabo.setName("Gabriel");
            gabo.setSurname("García Márquez");
            gabo.setNationality("Colombiano");
            gabo.setBiography("Premio Nobel de Literatura y máximo exponente del realismo mágico.");
            gabo.setBirthdate("1927-03-06");
            gabo.setCountry("Colombia");
            gabo.setCreatedAt(LocalDateTime.now());
            authorRepository.save(gabo);


            authorRepository.saveAll(Arrays.asList(tolkien, orwell, uncleBob, king, gabo));
            System.out.println(">>> AuthorSeeder: Autores iniciales creados.");
        }
    }
}