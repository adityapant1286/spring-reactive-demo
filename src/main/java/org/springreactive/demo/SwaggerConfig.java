package org.springreactive.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Spring Reactive Demo",
        version = "1.0",
        description = "A sample springboot functional reactive app")
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi carsGroupApi() {
        return GroupedOpenApi
                .builder()
                .group("Cars")
                .addOpenApiCustomizer(openApiCustomizer())
                .pathsToMatch("/cars/**")
                .build();
    }

    public OpenApiCustomizer openApiCustomizer() {
        return openAPI -> openAPI.getPaths().values()
                .stream()
                .flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> operation.addParametersItem(
                        new Parameter()
                                .name("Content-Type")
                                .in("header")
                                .schema(new StringSchema().example("application/json"))
                                .required(true)
                ));
    }
}
