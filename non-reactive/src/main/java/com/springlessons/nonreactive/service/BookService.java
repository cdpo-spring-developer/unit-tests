package com.springlessons.nonreactive.service;

import com.springlessons.nonreactive.dto.book.AuthorForClient;
import com.springlessons.nonreactive.dto.book.BookForClient;
import com.springlessons.nonreactive.entity.Book;
import com.springlessons.nonreactive.service.client.AuthorClient;
import com.springlessons.nonreactive.service.mapper.Mapper;
import com.springlessons.nonreactive.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final Mapper mapper;
    private final AuthorClient authorClient;

    public BookService(BookRepository bookRepository, Mapper mapper, AuthorClient authorClient) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
        this.authorClient = authorClient;
    }

    public BookForClient getBookById(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        AuthorForClient authorForClient = mapper.mapAuthor(
                authorClient.getAuthorById(
                        book.getAuthorId()));
        return mapper.mapBook(book, authorForClient);
    }
}

