package com.hotelbooking.search_service.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI searchServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hotel Search Service API")
                        .version("1.0.0")
                        .description("API documentation for the Hotel Search microservice")
                        .contact(new Contact()
                                .name("Prashant Thakur")
                                .url("https://github.com/PrashantThakurNitP")));
    }

    @Bean
    public GroupedOpenApi searchApiGroup() {
        return GroupedOpenApi.builder()
                .group("search-service")
                .pathsToMatch("/v1/search/**")
                .build();
    }
}
