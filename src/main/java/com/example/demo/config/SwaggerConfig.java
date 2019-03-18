package com.example.demo.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket produceApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(pathTransaksi())
                .build();
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Transaksi Nasabah Rest API")
                .description("Page ini menampilkan seluruh data Transaksi nasabah")
                .version("1.0-SNAPSHOT")
                .build();
    }

    //ONLY select API that matches URL

    private Predicate<String> pathTransaksi() {
    // Match all paths except /error
        return Predicates.and(PathSelectors.regex("/api/transaksi.*"),Predicates.not(PathSelectors.regex("/error.*")));
    }
}
