package com.springlessons.nonreactive.service.mapper;

import com.springlessons.nonreactive.dto.author.Attachment;
import com.springlessons.nonreactive.dto.author.AuthorFromOtherService;
import com.springlessons.nonreactive.dto.book.AuthorForClient;
import com.springlessons.nonreactive.dto.book.BookForClient;
import com.springlessons.nonreactive.entity.Book;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public AuthorForClient mapAuthor(AuthorFromOtherService authorFromOtherService) {
        return new AuthorForClient(
                authorFromOtherService.id(),
                authorFromOtherService.name(),
                authorFromOtherService.attachments().stream()
                        .filter(attachment -> attachment.attachmentType() == Attachment.AttachmentType.MAIN_PHOTO)
                        .map(Attachment::url)
                        .findAny()
                        .orElseThrow(() -> new RuntimeException("No attachment found for " +
                                authorFromOtherService.id()))
        );
    }

    public BookForClient mapBook(Book book, AuthorForClient authorForClient) {
        if (book == null || authorForClient == null)
            throw new RuntimeException("book and author are required");
        return new BookForClient(book.getId(),
                book.getTitle(), book.getYear(),
                authorForClient
        );
    }
}
