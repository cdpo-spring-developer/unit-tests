package com.springlessons.nonreactive.service.mapper;

import com.springlessons.nonreactive.dto.author.Attachment;
import com.springlessons.nonreactive.dto.author.AuthorFromOtherService;
import com.springlessons.nonreactive.dto.book.AuthorForClient;
import com.springlessons.nonreactive.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MapperTest {
    private Mapper mapper;

    @BeforeEach
    void init(){
        mapper = new Mapper();
    }

    void mapBook_ThrowsRuntimeException_NullParameters(){
        Assertions.assertThrows(
                RuntimeException.class,
                ()->mapper.mapBook(null, new AuthorForClient(1,"",""))
        );
        Assertions.assertThrows(
                RuntimeException.class,
                ()->mapper.mapBook(new Book(), null)
        );
        Assertions.assertThrows(
                RuntimeException.class,
                ()->mapper.mapBook(null, null)
        );
    }

    @Test
    void mapBook_ThrowsRuntimeException_NullBook(){
        Assertions.assertThrows(
                RuntimeException.class,
                ()->mapper.mapBook(null, new AuthorForClient(1,"",""))
        );
    }

    void mapBook_ThrowsRuntimeException_NullAuthor(){
        Assertions.assertThrows(
                RuntimeException.class,
                ()->mapper.mapBook(new Book(), null)
        );
    }

    // public AuthorForClient mapAuthor(AuthorFromOtherService authorFromOtherService)
    // new RuntimeException
    @Test
    void mapAuthor_ThrowsRuntimeException_NoMainPhoto() {
        AuthorFromOtherService authorFromOtherService =
                new AuthorFromOtherService(1, "author01", "about author01",
                        List.of(
                                new Attachment("mini_url", Attachment.AttachmentType.MINI_PHOTO),
                                new Attachment("video_url", Attachment.AttachmentType.VIDEO)
                        )
                );

        Assertions.assertThrows(
                RuntimeException.class,
                () -> mapper.mapAuthor(authorFromOtherService)
        );
    }

    @Test
    void mapAuthor_ReturnsAuthorForClient_ValidAuthorFromOS(){
        Attachment mainPhoto =
                new Attachment("main_url", Attachment.AttachmentType.MAIN_PHOTO);
        AuthorFromOtherService authorFromOtherService =
                new AuthorFromOtherService(1, "author01", "about author01",
                        List.of(mainPhoto)
                );

        AuthorForClient authorForClient = mapper.mapAuthor(authorFromOtherService);

        Assertions.assertNotNull(authorForClient);

        Assertions.assertEquals(1, authorForClient.id());
        Assertions.assertEquals("author01", authorForClient.name());
        Assertions.assertEquals("main_url", authorForClient.photoUrl());

    }
}
