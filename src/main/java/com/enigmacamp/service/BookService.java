package com.enigmacamp.service;

import com.enigmacamp.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    Page<Book> getAll(Integer page, Integer size, String direction, String sortBy) throws Exception;
    Book create(Book book) throws Exception;
    List<Book> getByTitle(String title) throws Exception;
    Book getById(String id) throws Exception;
    Book update(Book book, String id) throws Exception;
    void deleteById(String id) throws Exception;
}
