package com.enigmacamp.repository;

import com.enigmacamp.model.BookPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPriceRepository extends JpaRepository<BookPrice,String> {
}
