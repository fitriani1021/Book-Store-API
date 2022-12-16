package com.enigmacamp.repository;

import com.enigmacamp.model.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<BookStock,String> {
}
