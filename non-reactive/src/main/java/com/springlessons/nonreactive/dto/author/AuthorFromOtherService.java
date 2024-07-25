package com.springlessons.nonreactive.dto.author;

import java.util.List;

public record AuthorFromOtherService(int id, String name, String about, List<Attachment> attachments){
}
