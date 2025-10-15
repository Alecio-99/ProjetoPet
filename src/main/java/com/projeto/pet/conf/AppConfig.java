package com.projeto.pet.conf;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced // <-- MÁGICA: Permite usar nomes de serviço (ex: "http://planos-service" )
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
