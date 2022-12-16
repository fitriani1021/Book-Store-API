package com.enigmacamp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_price")
public class BookPrice {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "price_id")
    @Getter     @Setter
    private String priceId;
    @Column(name = "price")
    @Getter     @Setter
    private Integer price;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    @Getter     @Setter
    private BookStock stock;
}
