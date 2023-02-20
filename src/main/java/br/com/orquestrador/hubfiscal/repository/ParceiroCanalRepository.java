package br.com.orquestrador.hubfiscal.repository;

import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroCanalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@RequiredArgsConstructor
public class ParceiroCanalRepository {

    private final RestTemplate restTemplate;

    @Value("${parceiros.canal.url}")
    private String url;

    public String confirmarVenda(ParceiroCanalRequest parceiroCanalRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ParceiroCanalRequest> request = new HttpEntity<>(parceiroCanalRequest, headers);
        return restTemplate.postForObject(url, request, String.class);
    }
}
