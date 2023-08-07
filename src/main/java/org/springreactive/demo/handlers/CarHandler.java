package org.springreactive.demo.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springreactive.demo.models.Car;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class CarHandler {

    public Mono<ServerResponse> retrieve(ServerRequest request) {
        System.out.println(request.headers());
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(
                        Car.builder()
                                .brand("Mazda")
                                .model("3")
                                .engineSize(2500)
                                .features(List.of("Parking sensors", "Rear camera", "Active display"))
                                .build()
                ), Car.class);
    }
}
