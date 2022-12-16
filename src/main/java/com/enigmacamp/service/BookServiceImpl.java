package com.enigmacamp.service;

import com.enigmacamp.exception.NotFoundException;
import com.enigmacamp.model.Book;
import com.enigmacamp.model.BookGenre;
import com.enigmacamp.repository.BookRepository;
import com.enigmacamp.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private GenreRepository genreRepository;
    private BookRepository bookRepository;
    private ModelMapper modelMapper;
    
    public BookServiceImpl(@Autowired GenreRepository genreRepository, @Autowired BookRepository bookRepository, @Autowired ModelMapper modelMapper) {
        this.genreRepository = genreRepository;
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }
    
    @Override
    public Page<Book> getAll(Integer page, Integer size, String direction, String sortBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Book> result = bookRepository.findAll(pageable);
        return result;
    }
    
    @Override
    public Book create(Book book) throws Exception {
        Optional<BookGenre> genre = genreRepository.findByName(book.getGenre().getName());
        if(genre.isEmpty()) {
            throw new NotFoundException("Genre is Not Found");
        }
        book.setGenre(genre.get());
        Book newBook = bookRepository.save(book);
        return newBook;
    }
    
    @Override
    public List<Book> getByTitle(String title) throws Exception {
        List<Book> result = bookRepository.getByTitle(title);
        if(result.isEmpty()) {
            throw new NotFoundException("Genre " + title + " not found");
        }
        return result;
    }
    
    @Override
    public Book getById(String id) throws Exception {
        Optional<Book> result = bookRepository.findById(id);
        if(result.isEmpty()) {
            throw new NotFoundException("Genre " + id + " not found");
        }
        return result.get();
    }
    
    @Override
    public Book update(Book book, String id) throws Exception {
        try {
            Book existingBook = getById(id);
            modelMapper.map(book, existingBook);
            book.setBookId(id);
            Book newBook = bookRepository.save(existingBook);
            return newBook;
        } catch(NotFoundException e) {
            throw new NotFoundException("Update failed because ID is not found");
        }
    }
    
    @Override
    public void deleteById(String id) throws Exception {
        try {
            Book existingCourse = getById(id);
            bookRepository.delete(existingCourse);
            System.out.println("Successfully deleted");
        } catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }
}
