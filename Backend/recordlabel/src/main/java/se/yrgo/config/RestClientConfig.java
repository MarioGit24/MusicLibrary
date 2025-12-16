package se.yrgo.config;

import org.springframework.context.annotation.*;
import org.springframework.web.client.*;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}