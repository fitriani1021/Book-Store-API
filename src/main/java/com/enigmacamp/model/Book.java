package com.enigmacamp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_book")
@ToString
public class Book {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "book_id")
    @Getter
    @Setter
    private String bookId;
    @Column(name = "title")
    @Getter
    @Setter
    private String title;
    @Column(name = "author")
    @Getter
    @Setter
    private String author;
    @Column(name = "pub_year")
    @Getter
    @Setter
    private String pubYear;
    @Column(name = "publisher")
    @Getter
    @Setter
    private String publisher;
    @Column(name = "isbn")
    @Getter
    @Setter
    private String isbn;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_genre")
    @JsonBackReference
    @Getter
    @Setter
    private BookGenre genre;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    @Getter
    @Setter
    private BookPrice price;
}
