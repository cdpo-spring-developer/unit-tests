package com.springlessons.nonreactive.dto.book;

public record BookForClient(int id,
                            String title,
                            int year,
                            AuthorForClient author) {
}
