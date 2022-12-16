package com.enigmacamp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_stock")
public class BookStock {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "stock_id")
    @Getter     @Setter
    private String stockId;
    @Column(name = "stock")
    @Getter     @Setter
    private Integer stock;
}
