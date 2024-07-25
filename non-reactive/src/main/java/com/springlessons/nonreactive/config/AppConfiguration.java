package com.springlessons.nonreactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfiguration {

    @Bean
    public RestClient restClient(){
        return RestClient.create("http://localhost:8081/");
    }
}
