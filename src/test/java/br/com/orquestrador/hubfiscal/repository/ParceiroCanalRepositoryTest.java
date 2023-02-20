package br.com.orquestrador.hubfiscal.repository;

import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroCanalRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class ParceiroCanalRepositoryTest {

    private String url;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ParceiroCanalRepository repository;

    @Test
    void deveRetornarMensagemDoCanalQuandoChamarConfirmarVenda() {
        Mockito.when(restTemplate.postForObject(url, mockHttpRequest(), String.class)).thenReturn("responseCanal");
        String retorno = repository.confirmarVenda(mockCanalRequest());
        Assertions.assertEquals("responseCanal", retorno);
        Mockito.verify(restTemplate, Mockito.times(1)).postForObject(url, mockHttpRequest(), String.class);
    }

    private ParceiroCanalRequest mockCanalRequest() {
        return ParceiroCanalRequest.builder().build();
    }

    private HttpEntity<ParceiroCanalRequest> mockHttpRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(mockCanalRequest(), headers);
    }
}
