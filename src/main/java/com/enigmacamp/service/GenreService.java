package com.enigmacamp.service;

import com.enigmacamp.model.BookGenre;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface GenreService {
    BookGenre create(BookGenre category) throws Exception;
    Page<BookGenre> getAll(Integer page, Integer size, String direction, String sortBy) throws Exception;
    BookGenre getByName(String name) throws Exception;
    Optional<BookGenre> getById(String id) throws Exception;
    void deleteById(String id) throws Exception;
}
