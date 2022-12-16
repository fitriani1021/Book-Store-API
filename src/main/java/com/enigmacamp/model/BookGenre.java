package com.enigmacamp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "m_genre")
public class BookGenre {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "genre_id", unique = true)
    @Getter     @Setter
    private String genreId;
    @Column(name = "genre_name", unique = true)
    @Getter     @Setter
    private String name;
    @OneToMany(mappedBy = "genre", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    @Getter     @Setter
    private List<Book> bookList;
}
