package com.project.apibookshop.repository;

import com.project.apibookshop.model.Book_Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepository extends JpaRepository<Book_Author, Long> {
}
