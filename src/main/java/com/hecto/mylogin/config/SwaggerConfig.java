package com.hecto.mylogin.config;

// http://localhost:8080/swagger-ui/index.html

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    /*
     * OpenAPI bean 구성
     * @return
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Swagger Test")
                                .description("API에 대한 설명 부분")
                                .version("v1.0.0"));
    }
}
