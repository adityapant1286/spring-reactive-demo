package org.springreactive.demo.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springreactive.demo.SpringReactiveDemoApplication;
import org.springreactive.demo.models.Car;
import org.springreactive.demo.routes.CarRouteConfig;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringReactiveDemoApplication.class)
@ActiveProfiles({"dev", "test"})
class CarHandlerTest {

    @Value("${spring.webflux.base-path}")
    private String basePath;

    @Autowired
    CarRouteConfig config;

    @Autowired
    CarHandler handler;

    WebTestClient client;

    @BeforeEach
    void setup() {
        client = WebTestClient.bindToRouterFunction(config.routerFunctions(handler))
                .build();
    }

    @Test
    void testRetrieve() {
        System.out.println(basePath);

        final Flux<Car> response = client
                .get()
                .uri("/cars")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Car.class)
                .getResponseBody();

        StepVerifier.create(response)
                .expectSubscription()
                .expectNextMatches(car -> car.getBrand().equals("Mazda") && car.getModel().equals("3"))
                .verifyComplete();
    }
}