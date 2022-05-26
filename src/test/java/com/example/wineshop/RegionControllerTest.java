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
public class RegionControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private RegionRepository repository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void mostrarTodosOk() {
        webTestClient.get()
                .uri("/regions")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void mostrarTodosHalJson(){
        webTestClient.get()
                .uri("/regions")
                .exchange()
                .expectHeader().valueEquals("Content-Type", "application/hal+json");
    }

    @Test
    void getOne() {
        webTestClient.get()
                .uri("/regions/5")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void oneCheckId(){
        webTestClient.get()
                .uri("/regions/5")
                .exchange()
                .expectBody()
                .jsonPath("$.id").isEqualTo(5);
    }

    @Test
    void getOneCheckName(){
        webTestClient.get()
                .uri("/regions/5")
                .exchange()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Malvarrosa");
    }

    @Test
    void getOneCheckCountry(){
        webTestClient.get()
                .uri("/regions/5")
                .exchange()
                .expectBody()
                .jsonPath("$.country").isEqualTo("España");
    }

    @Test
    void getNonExistentStatus() {
        webTestClient.get()
                .uri("/regions/65")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void getNonExistentOne(){
        webTestClient.get()
                .uri("/regions/65")
                .exchange()
                .expectBody(String.class).isEqualTo("Could not find region 65");
    }

    @Test
    void getWrongDatatypeOne(){
        webTestClient.get()
                .uri("/regions/Poteitos")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void deleteNonExistent(){
        webTestClient.delete()
                .uri("/regions/404")
                .exchange()
                .expectStatus().isNotFound();
        repository.findAll().forEach(x -> System.out.println(x.toString()));
    }

    @Test
    void deleteOne(){
        webTestClient.delete()
                .uri("/regions/6")
                .exchange()
                .expectStatus().isNoContent();
        repository.findAll().forEach(x -> System.out.println(x.toString()));

    }
}
