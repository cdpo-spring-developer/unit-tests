package com.springlessons.nonreactive.service;

import com.springlessons.nonreactive.dto.author.Attachment;
import com.springlessons.nonreactive.dto.author.AuthorFromOtherService;
import com.springlessons.nonreactive.dto.book.AuthorForClient;
import com.springlessons.nonreactive.dto.book.BookForClient;
import com.springlessons.nonreactive.entity.Book;
import com.springlessons.nonreactive.repository.BookRepository;
import com.springlessons.nonreactive.service.client.AuthorClient;
import com.springlessons.nonreactive.service.mapper.Mapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BookServiceTest {

    @InjectMocks
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private Mapper mapper;
    @Mock
    private AuthorClient authorClient;

    @Test
    void getBookById_ThrowsRuntimeException_NotPositiveId() {
        Mockito.when(bookRepository.findById(-1))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                RuntimeException.class,
                () -> bookService.getBookById(-1)
        );
    }

    @Test
    @SneakyThrows
    void getBookById_ReturnsValid_BookForClient() {
        Book book = new Book();
        book.setId(1);
        book.setAuthorId(4);

        Mockito.when(bookRepository.findById(1))
                .thenReturn(Optional.of(book));

        AuthorFromOtherService authorFromOtherService =
                new AuthorFromOtherService(4, "", "",
                        List.of(
                                new Attachment("", Attachment.AttachmentType.MAIN_PHOTO)
                        ));
        Mockito.when(authorClient.getAuthorById(Mockito.anyInt()))
                .thenReturn(authorFromOtherService);

        AuthorForClient authorForClient = new AuthorForClient(
                4, "", ""
        );
        Mockito.when(mapper.mapAuthor(Mockito.any(AuthorFromOtherService.class)))
                .thenReturn(authorForClient);

        Mockito.when(mapper.mapBook(book, authorForClient))
                .thenReturn(new BookForClient(1, "", 1, authorForClient));

        Method method = ReflectionUtils
                .findMethod(BookService.class, "sum", int.class, int.class).orElseThrow();
        method.setAccessible(true);

        int result = (int) method.invoke(bookService, book.getId(), authorForClient.id());

        Assertions.assertEquals(result + 3, bookService.getBookById(1));
    }
}
