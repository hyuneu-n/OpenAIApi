package com.example.openapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@SpringBootApplication
public class OpenapiApplication {

    @Value("${openai.key}")
    private String openApiKey;

    public static void main(String[] args) {
        SpringApplication.run(OpenapiApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getInterceptors().add(((request, body, execution) -> {
            request.getHeaders().add("Authorization",
                    "Bearer " + openApiKey);
            return  execution.execute(request,body);
        }));
        return restTemplate;
    }
}
