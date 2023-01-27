package com.facele.docele.webxpc;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    
    @Value("${server.servlet.context-path}")
    String contextPath;

    @Bean
    public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .select()                                  
              .apis(RequestHandlerSelectors.basePackage("com.facele.docele.webxpc.controller"))              
              .paths(PathSelectors.any())  
              
              .build()
              .pathProvider(new RelativePathProvider(null) {
                  @Override
                  public String getApplicationBasePath() {
                      return contextPath;
                  }
              })
              .apiInfo(apiInfo()).securitySchemes(Arrays.asList(apiKey()));

    }
    
    private ApiKey apiKey() {
        return new ApiKey("jwtToken", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
         return new ApiInfo(
           "CL-webxpc rest API", 
           "Sistema de excepciones", 
           "0.0.1", 
           "facele.cl", 
           new Contact("Grupo Facele", "www.docele.com", "info@docele.com"), 
           "License of API", "API license URL", Collections.emptyList());
    }
}
