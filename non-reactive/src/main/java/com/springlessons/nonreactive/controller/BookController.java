package com.springlessons.nonreactive.controller;

import com.springlessons.nonreactive.dto.book.BookForClient;
import com.springlessons.nonreactive.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/{id}")
    public BookForClient getById(@PathVariable int id) {
        return bookService.getBookById(id);
    }
}
