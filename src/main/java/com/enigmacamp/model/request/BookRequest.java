package com.enigmacamp.model.request;

import lombok.Data;

@Data
public class BookRequest {
    private String title;
    private String author;
    private String pubYear;
    private String publisher;
    private String isbn;
    private BookGenreRequest genre;
    private BookPriceRequest price;
    private BookStockRequest stock;
}
