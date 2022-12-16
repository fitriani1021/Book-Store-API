package com.enigmacamp.controller;

import com.enigmacamp.model.BookGenre;
import com.enigmacamp.model.request.BookGenreRequest;
import com.enigmacamp.model.response.ErrorResponse;
import com.enigmacamp.model.response.PagingResponse;
import com.enigmacamp.model.response.SuccessResponse;
import com.enigmacamp.service.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private GenreService genreService;
    private ModelMapper modelMapper;
    
    public GenreController(@Autowired GenreService genreService, @Autowired ModelMapper modelMapper) {
        this.genreService = genreService;
        this.modelMapper = modelMapper;
    }
    
    @GetMapping
    public ResponseEntity getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size, @RequestParam(defaultValue = "ASC") String direction, @RequestParam(defaultValue = "name") String sortBy) throws Exception {
        Page<BookGenre> result = genreService.getAll(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Successfully Get All Genre", result));
    }
    
    @PostMapping
    public ResponseEntity add(@RequestBody BookGenreRequest bookCategory) throws Exception {
        try {
            BookGenre result = modelMapper.map(bookCategory, BookGenre.class);
            genreService.create(result);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully Created Genre", result));
        }catch(DataIntegrityViolationException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("X07", "Genre is exists"));
        }
    }
    
    @GetMapping("/name={name}")
    public ResponseEntity getByName(@PathVariable("name") String name) {
        try {
            BookGenre result = genreService.getByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find Category By Name", result));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X06", "Genre not found"));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") String id) {
        try {
            Optional<BookGenre> result = genreService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Find Category By ID", result));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X06", "Genre not found"));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") String id) throws Exception {
        try {
            genreService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Delete Success");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X06","Id not found"));
        }
    }
}
