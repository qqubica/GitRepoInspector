package com.example.gitrepoinspector.configuration;

import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class GitApiWebClient {
    String baseUrl = "https://api.github.com/";
    @Value("${git.token}")
    String token = "";

    @Bean
    public WebClient webClient() {
        WebClient.Builder webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("X-GitHub-Api-Version", "2022-11-28");
        if (!token.isEmpty()) {
            webClient.defaultHeader("Authorization", "Bearer " + token);
        }
        return webClient.build();
    }

}
