package com.project.apibookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.apibookshop.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
    @Query("SELECT ba.book FROM Book_Author ba WHERE ba.author.name = :name AND ba.author.surname = :surname")
    List<Book> findByAuthorNameAndSurname(@Param("name") String name, @Param("surname") String surname);
    List<Book> findByGenre(String genre);

}
