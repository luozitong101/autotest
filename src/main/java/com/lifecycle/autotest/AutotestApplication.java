package com.lifecycle.autotest;

import com.lifecycle.autotest.bean.CustomClientHttpRequestInterceptor;
import com.lifecycle.autotest.bean.CustomRestTemplateCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author luyong
 */
@SpringBootApplication
public class AutotestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutotestApplication.class, args);
    }


    @Autowired
    CustomClientHttpRequestInterceptor customClientHttpRequestInterceptor;


//    @Bean
//    public CustomRestTemplateCustomizer customRestTemplateCustomizer() {
//        return new CustomRestTemplateCustomizer();
//    }

//    @Bean
//    @DependsOn(value = {"customRestTemplateCustomizer"})
//    public RestTemplateBuilder restTemplateBuilder() {
//        return new RestTemplateBuilder(customRestTemplateCustomizer());
//    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(customClientHttpRequestInterceptor));
        return restTemplate;
    }
}
