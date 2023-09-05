package com.example.gitrepoinspector.service;

import com.example.gitrepoinspector.model.DTOs.GitBranch;
import com.example.gitrepoinspector.model.DTOs.GitRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class GitHubApiService {
    WebClient webClient;
    String brancheUrl = "repos/{username}/{repositoryName}/branches";
    String repositoryUrl = "users/{username}/repos";

    public GitHubApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<GitRepository> listSelectedRepositories(String username, String acceptHeader) {
        if (acceptHeader.equals("application/xml")) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "accept: application/xml header is not supported");
        }

        return webClient.get()
                .uri(repositoryUrl, username)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(response -> response.equals(HttpStatus.NOT_FOUND), r -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "username not found on github");
                })
                .bodyToFlux(GitRepository.class)
                .filter(repository -> !repository.isFork())
                .flatMapSequential(
                        repo -> webClient.get()
                                .uri(brancheUrl, username, repo.getRepositoryName())
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .bodyToFlux(GitBranch.class)
                                .collectList()
                                .map(branch -> {
                                    repo.setBranches(branch);
                                    return repo;
                                }));
    }

}
