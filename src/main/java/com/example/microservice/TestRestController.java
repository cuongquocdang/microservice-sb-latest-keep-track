package com.example.microservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TestRestController {

    private final TestClient testClient;

    @GetMapping("/posts")
    public List<TestClient.PostDTO> retrievePosts() {
        return List.of(
                testClient.fetchPostById(1),
                testClient.fetchPostById(2),
                testClient.fetchPostById(3)
        );
    }
}
