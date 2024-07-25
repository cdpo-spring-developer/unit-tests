package com.springlessons.nonreactive.dto.author;

public record Attachment(String url, AttachmentType attachmentType) {

    public enum AttachmentType {
        MAIN_PHOTO, MINI_PHOTO, VIDEO
    }
}
