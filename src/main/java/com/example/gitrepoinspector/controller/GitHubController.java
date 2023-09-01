package com.example.gitrepoinspector.controller;

import com.example.gitrepoinspector.service.GitHubApiService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/")
public class GitHubController {
    private final GitHubApiService gitHubApiService;

    public GitHubController(GitHubApiService gitHubApiService) {
        this.gitHubApiService = gitHubApiService;
    }

    @GetMapping(value = "/repositories/{username}", produces = {"application/json"}, consumes = {"application/json", "*/*"})
    public Mono<?> getUserRepositories(@PathVariable String username, @RequestHeader(HttpHeaders.ACCEPT) String acceptHeader) {
        return gitHubApiService.listSelectedRepositories(username, acceptHeader);
    }

}
