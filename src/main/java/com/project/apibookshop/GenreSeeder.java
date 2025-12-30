package com.project.apibookshop;

import com.project.apibookshop.model.Genre;
import com.project.apibookshop.repository.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GenreSeeder implements CommandLineRunner {

    private final GenreRepository genreRepository;

    public GenreSeeder(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(String... args) {

        if (genreRepository.count() == 0) {
            List<String> genresNames = Arrays.asList(
                    "Fiction", "Fantasy", "Terror", "Science Fiction",
                    "Romance", "Adventure", "History", "Biography",
                    "Policial", "Selfcare", "Classics", "For kids",
                    "Maths", "Physics", "Chemistry", "Programming"
            );

            List<Genre> genres = genresNames.stream()
                    .map(name -> Genre.builder().name(name).build())
                    .toList();

            genreRepository.saveAll(genres);
            System.out.println(">>> GenreSeeder: " + genresNames.size() + " created succesfully" +
                    "!");
        }
    }
}