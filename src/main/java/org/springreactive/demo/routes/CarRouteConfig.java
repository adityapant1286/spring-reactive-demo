package org.springreactive.demo.routes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springreactive.demo.handlers.CarHandler;
import org.springreactive.demo.models.Car;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@Profile({"dev", "test"})
public class CarRouteConfig {

    @Bean
    @RouterOperation(
            path = "/cars",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            beanClass = CarHandler.class,
            beanMethod = "retrieve",
            operation = @Operation(
                    operationId = "retrieve",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "success",
                                    content = @Content(schema = @Schema(implementation = Car.class))
                            ),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Not found"
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> routerFunctions(CarHandler handler) {

        return RouterFunctions
                .route(
                        GET(String.format("/%s", "cars"))
                                .and(accept(MediaType.APPLICATION_JSON)),
                        handler::retrieve
                );
    }
}
