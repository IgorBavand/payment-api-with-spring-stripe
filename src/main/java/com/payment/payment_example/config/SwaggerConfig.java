package com.payment.payment_example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final List<String> PATHS = Arrays.asList("/v2/api-docs", "/swagger-resources",
            "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security", "/swagger-ui.html",
            "/webjars/springfox-swagger-ui/favicon-32x32.png", "/webjars/springfox-swagger-ui/swagger-ui-bundle.js",
            "/webjars/springfox-swagger-ui/swagger-ui.css",
            "/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js", "/webjars/**/**");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.payment.payment_example"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(apiKey())
                ).apiInfo(metaData());
    }


    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Payments API")
                .description("API for payments")
                .version("1.0.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer Token", HttpHeaders.AUTHORIZATION, "header");
    }
}