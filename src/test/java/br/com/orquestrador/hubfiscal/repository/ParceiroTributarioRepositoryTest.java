package br.com.orquestrador.hubfiscal.repository;

import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroTributarioResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ParceiroTributarioRepositoryTest {

    private String url;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    ParceiroTributarioRepository repository;

    @Test
    void deveRetornarObjetoParceiroTributarioResponseQuandoChamarobterMatrizTributaria() {
        Integer sku = 55;
        Mockito.when(restTemplate.getForObject(url, ParceiroTributarioResponse.class, Map.of("sku", sku))).thenReturn(mockResponse());
        ParceiroTributarioResponse retorno = repository.obterMatrizTributaria(sku);
        Assertions.assertNotNull(retorno);
        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(url, ParceiroTributarioResponse.class, Map.of("sku", sku));

    }

    private ParceiroTributarioResponse mockResponse() {
        return ParceiroTributarioResponse.builder().build();
    }
}
