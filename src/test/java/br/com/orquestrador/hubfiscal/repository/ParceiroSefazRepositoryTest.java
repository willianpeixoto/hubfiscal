package br.com.orquestrador.hubfiscal.repository;

import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroSefazCustomerRequest;
import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroSefazProductsRequest;
import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroSefazRequest;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroSefazResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ParceiroSefazRepositoryTest {

    private String url;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    ParceiroSefazRepository repository;

    @Test
    void deveRetornarObjetoParceiroSefazResponseQuandoChamarObterAutorizacao() {
        Mockito.when(restTemplate.postForObject(url, mockHttpRequest(), ParceiroSefazResponse.class)).thenReturn(mockResponse());
        ParceiroSefazResponse retorno = repository.obterAutorizacao(mockSefazRequest());
        Assertions.assertNotNull(retorno);
        Mockito.verify(restTemplate, Mockito.times(1)).postForObject(url, mockHttpRequest(), ParceiroSefazResponse.class);
    }

    private ParceiroSefazRequest mockSefazRequest() {
        return ParceiroSefazRequest.builder()
                .externalOrderNumber("123456789")
                .externalOrderNumber("123123123")
                .customer(mockSefazCustomer())
                .products(mockListaSefazProducts())
                .build();
    }

    private ParceiroSefazCustomerRequest mockSefazCustomer() {
        return ParceiroSefazCustomerRequest.builder()
                .id("123")
                .build();
    }

    private List<ParceiroSefazProductsRequest> mockListaSefazProducts() {
        return Arrays.asList(mockSefazProducts());
    }

    private ParceiroSefazProductsRequest mockSefazProducts() {
        return ParceiroSefazProductsRequest.builder()
                .sku(123456)
                .build();
    }

    private HttpEntity<ParceiroSefazRequest> mockHttpRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(mockSefazRequest(), headers);
    }

    private ParceiroSefazResponse mockResponse() {
        return new ParceiroSefazResponse();
    }
}
