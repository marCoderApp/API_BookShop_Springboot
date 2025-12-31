package com.project.apibookshop.repository;

import com.project.apibookshop.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {



}
