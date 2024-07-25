package com.springlessons.nonreactive.repository;

import com.springlessons.nonreactive.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
