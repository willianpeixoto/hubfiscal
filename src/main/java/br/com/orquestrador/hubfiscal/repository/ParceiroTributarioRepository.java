package br.com.orquestrador.hubfiscal.repository;

import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroTributarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ParceiroTributarioRepository {

    private final RestTemplate restTemplate;

    @Value("${parceiros.tributario.url}")
    private String url;

    public ParceiroTributarioResponse obterMatrizTributaria(Integer sku) {
        return restTemplate.getForObject(url, ParceiroTributarioResponse.class, Map.of("sku", sku));
    }
}
