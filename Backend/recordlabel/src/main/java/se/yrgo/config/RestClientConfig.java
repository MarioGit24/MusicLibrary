package main.java.se.yrgo.config;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
