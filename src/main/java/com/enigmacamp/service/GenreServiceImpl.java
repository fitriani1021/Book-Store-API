package com.enigmacamp.service;

import com.enigmacamp.exception.EntityExistException;
import com.enigmacamp.exception.NotFoundException;
import com.enigmacamp.model.Book;
import com.enigmacamp.model.BookGenre;
import com.enigmacamp.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;
    
    public GenreServiceImpl(@Autowired GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    
    @Override
    @Transactional
    public BookGenre create(BookGenre category) throws Exception {
        try {
            BookGenre newCategory = genreRepository.save(category);
            return newCategory;
        } catch(DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }
    
    @Override
    @Transactional
    public Page<BookGenre> getAll(Integer page, Integer size, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<BookGenre> result = genreRepository.findAll(pageable);
        return result;
    }
    
    @Override
    @Transactional
    public BookGenre getByName(String name) throws Exception {
        Optional<BookGenre> category = genreRepository.findByName(name);
        if(category.isEmpty()) {
            throw new NotFoundException("Genre " + name + " not found");
        }
        return category.get();
    }
    
    @Override
    @Transactional
    public Optional<BookGenre> getById(String id) throws Exception {
        Optional<BookGenre> genre = genreRepository.findById(id);
        if(genre.isEmpty()) {
            throw new NotFoundException("Course " + id + " not found");
        }
        return genre;
    }
    
    @Override
    @Transactional
    public void deleteById(String id) throws Exception {
        try {
            genreRepository.deleteById(id);
            System.out.println("Successfully deleted");
        } catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }
}
