package com.example.wineshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class WineryControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private WineryRepository repository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void mostrarTodosOk() {
        webTestClient.get()
                .uri("/wineries")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void mostrarTodosHalJson(){
        webTestClient.get()
                .uri("/wineries")
                .exchange()
                .expectHeader().valueEquals("Content-Type", "application/hal+json");
    }

    @Test
    void getOne() {
        webTestClient.get()
                .uri("/wineries/3")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void oneCheckId(){
        webTestClient.get()
                .uri("/wineries/3")
                .exchange()
                .expectBody()
                .jsonPath("$.id").isEqualTo(3);
    }

    @Test
    void getOneCheckName(){
        webTestClient.get()
                .uri("/wineries/3")
                .exchange()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Hacendado");
    }

    @Test
    void getNonExistentStatus() {
        webTestClient.get()
                .uri("/wineries/65")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void getNonExistentOne(){
        webTestClient.get()
                .uri("/wineries/65")
                .exchange()
                .expectBody(String.class).isEqualTo("Could not find winery 65");
    }

    @Test
    void getWrongDatatypetOne(){
        webTestClient.get()
                .uri("/wineries/Poteitos")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void deleteNonExistent(){
        webTestClient.delete()
                .uri("/wineries/404")
                .exchange()
                .expectStatus().isNotFound();
        repository.findAll().forEach(x -> System.out.println(x.toString()));
    }

    @Test
    void deleteOne(){
        webTestClient.delete()
                .uri("/wineries/4")
                .exchange()
                .expectStatus().isNoContent();
        repository.findAll().forEach(x -> System.out.println(x.toString()));

    }
}
