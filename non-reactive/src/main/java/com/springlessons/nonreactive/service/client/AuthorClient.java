package com.springlessons.nonreactive.service.client;

import com.springlessons.nonreactive.dto.author.AuthorFromOtherService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthorClient {
    private final RestClient restClient;

    public AuthorClient(RestClient restClient) {
        this.restClient = restClient;
    }

    // List<Book>
    // Optional<Book>
    // Book<Integer>

    public AuthorFromOtherService getAuthorById(int id) {
        return restClient.get()
               // .header("", "")
                .uri("/author/{id}", id)
                .retrieve()
                .body(AuthorFromOtherService.class);
    }
}
