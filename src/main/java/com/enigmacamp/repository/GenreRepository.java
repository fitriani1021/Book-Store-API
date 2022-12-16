package com.enigmacamp.repository;

import com.enigmacamp.model.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<BookGenre,String> {
    Optional<BookGenre> findByName(String name);
}
