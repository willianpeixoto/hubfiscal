package br.com.orquestrador.hubfiscal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class HubConfig {

    @Value("${hub-fiscal.data-execucao-sistema}")
    private String dtExecucaoSistema;

    @Bean
    public String dtExecucaoSistema() {
        return dtExecucaoSistema;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }
}
