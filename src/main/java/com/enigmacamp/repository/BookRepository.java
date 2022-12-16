package com.enigmacamp.repository;

import com.enigmacamp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,String> {
    List<Book> getByTitle(String title);
}