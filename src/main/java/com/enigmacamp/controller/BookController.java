package com.enigmacamp.controller;

import com.enigmacamp.exception.NotFoundException;
import com.enigmacamp.model.Book;
import com.enigmacamp.model.request.BookRequest;
import com.enigmacamp.model.response.ErrorResponse;
import com.enigmacamp.model.response.PagingResponse;
import com.enigmacamp.model.response.SuccessResponse;
import com.enigmacamp.service.BookService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;
    private ModelMapper modelMapper;
    
    public BookController(@Autowired BookService bookService, @Autowired ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }
    
    @GetMapping
    public ResponseEntity getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, @RequestParam(defaultValue = "ASC") String direction, @RequestParam(defaultValue = "title") String sortBy) throws Exception {
        Page<Book> bookList = bookService.getAll(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Successfully Get All Books", bookList));
    }
    
    @PostMapping
    public ResponseEntity addBook(@Valid @RequestBody BookRequest book) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Book newBook = modelMapper.map(book, Book.class);
        Book result = bookService.create(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully created book", result));
    }
    
    @GetMapping("/title={title}")
    public ResponseEntity getBookByTitle(@PathVariable("title") String title) throws Exception {
        try {
            List<Book> bookList = bookService.getByTitle(title);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully Get Book By Title", bookList));
        } catch(NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X06", title + " is not found"));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getBookById(@PathVariable("id") String id) throws Exception {
        try {
            Book book = bookService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully Get Book By ID", book));
        }catch(Exception e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X06","ID is not found"));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity updateBook(@PathVariable("id") String id, @Valid @RequestBody BookRequest book) throws Exception {
        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            Book newBook = modelMapper.map(book, Book.class);
            Book result = bookService.update(newBook, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully updated book", result));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X06","Id not found"));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") String id) throws Exception {
        try {
            bookService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new SuccessResponse<>("Successfully deleted", "ID " + id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X06","Id not found"));
        }
    }
}
