package br.com.orquestrador.hubfiscal.repository;

import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroSefazRequest;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroSefazResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@RequiredArgsConstructor
public class ParceiroSefazRepository {

    private final RestTemplate restTemplate;

    @Value("${parceiros.sefaz.url}")
    private String url;

    public ParceiroSefazResponse obterAutorizacao(ParceiroSefazRequest parceiroSefazRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ParceiroSefazRequest> request = new HttpEntity<>(parceiroSefazRequest, headers);
        return restTemplate.postForObject(url, request, ParceiroSefazResponse.class);
    }
}
