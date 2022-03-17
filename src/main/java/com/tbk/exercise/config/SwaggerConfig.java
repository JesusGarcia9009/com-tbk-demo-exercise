package com.tbk.exercise.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Jesus Garcia
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DEFAULT_INCLUDE_PATTERN = "/*.*";

	/**
     * Publish a bean to generate swagger2 endpoints
     * @return a swagger configuration bean
     */
    @Bean
    public Docket usersApi() {
    	
    	
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(usersApiInfo())
                .select()
                //.paths(userPaths())
                .apis(RequestHandlerSelectors
                        .basePackage("com.tbk.exercise.restcontroller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .useDefaultResponseMessages(false);
    }
    
    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }
    
    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
            .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
            = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
            new SecurityReference("JWT", authorizationScopes));
    }


    /**
     * Api info
     * @return ApiInfo
     */
    private ApiInfo usersApiInfo() {
    	Contact contact = new Contact(
                "Jesus Garcia",
                "https://github.com/JesusGarcia9009/com-tbk-demo-exercise",
                "https://github.com/JesusGarcia9009/com-tbk-demo-exercise");
        return new ApiInfoBuilder()
                .title("Microservicio de registro y autenficacion de usuarios")
                .version("1.0")
                .contact(contact)
                .license("Apache License Version 2.0")
                .build();
    }
}