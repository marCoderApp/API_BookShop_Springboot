package com.project.apibookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.apibookshop.model.Book;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

}
