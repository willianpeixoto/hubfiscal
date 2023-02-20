package br.com.orquestrador.hubfiscal.service;

import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroSefazResponse;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroTributarioResponse;
import br.com.orquestrador.hubfiscal.repository.ParceiroCanalRepository;
import br.com.orquestrador.hubfiscal.repository.ParceiroSefazRepository;
import br.com.orquestrador.hubfiscal.repository.ParceiroTributarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ProcessarAutorizacaoVendaServiceTest {

    @Mock
    private ParceiroTributarioRepository parceiroTributarioRepository;

    @Mock
    private ParceiroSefazRepository parceiroSefazRepository;

    @Mock
    private ParceiroCanalRepository parceiroCanalRepository;

    @Mock
    private InformacoesVendaService informacoesVendaService;

    @InjectMocks
    private ProcessarAutorizacaoVendaService service;

    @BeforeEach
    void setUp() {
        service = new ProcessarAutorizacaoVendaService(
                parceiroTributarioRepository,
                parceiroSefazRepository,
                parceiroCanalRepository,
                informacoesVendaService,
                "2022-11-11T15:47:10");
    }

    @Test
    void deveGravarInformacoesDaVendaQuandoProcessarAutorizacaoComSucesso() {
        Mockito.when(parceiroTributarioRepository.obterMatrizTributaria(Mockito.anyInt())).thenReturn(mockTributarioResponse());
        Mockito.when(parceiroSefazRepository.obterAutorizacao(Mockito.any())).thenReturn(new ParceiroSefazResponse());
        Mockito.when(parceiroCanalRepository.confirmarVenda(Mockito.any())).thenReturn("OK");

        service.processarAutorizacaoVenda(mockVendaRequestJson());
        Mockito.verify(parceiroTributarioRepository, Mockito.times(3)).obterMatrizTributaria(Mockito.anyInt());
        Mockito.verify(parceiroSefazRepository, Mockito.times(1)).obterAutorizacao(Mockito.any());
        Mockito.verify(parceiroCanalRepository, Mockito.times(1)).confirmarVenda(Mockito.any());
        Mockito.verify(informacoesVendaService, Mockito.times(1)).gravaAtualizaInformacoesVenda(Mockito.any());
    }

    @Test
    void deveGravarInformacoesDaVendaQuandoProcessarAutorizacaoEOcorrerErroCausadoPeloParceiroTributario() {
        Mockito.when(parceiroTributarioRepository.obterMatrizTributaria(Mockito.anyInt())).thenThrow(NullPointerException.class);

        service.processarAutorizacaoVenda(mockVendaRequestJson());
        Mockito.verify(parceiroTributarioRepository, Mockito.times(1)).obterMatrizTributaria(Mockito.anyInt());
        Mockito.verify(parceiroSefazRepository, Mockito.times(0)).obterAutorizacao(Mockito.any());
        Mockito.verify(parceiroCanalRepository, Mockito.times(0)).confirmarVenda(Mockito.any());
        Mockito.verify(informacoesVendaService, Mockito.times(1)).gravaAtualizaInformacoesVenda(Mockito.any());
    }

    @Test
    void deveGravarInformacoesDaVendaQuandoProcessarAutorizacaoEOcorrerErroCausadoPeloParceiroSefaz() {
        Mockito.when(parceiroTributarioRepository.obterMatrizTributaria(Mockito.anyInt())).thenReturn(mockTributarioResponse());
        Mockito.when(parceiroSefazRepository.obterAutorizacao(Mockito.any())).thenThrow(NullPointerException.class);

        service.processarAutorizacaoVenda(mockVendaRequestJson());
        Mockito.verify(parceiroTributarioRepository, Mockito.times(3)).obterMatrizTributaria(Mockito.anyInt());
        Mockito.verify(parceiroSefazRepository, Mockito.times(1)).obterAutorizacao(Mockito.any());
        Mockito.verify(parceiroCanalRepository, Mockito.times(0)).confirmarVenda(Mockito.any());
        Mockito.verify(informacoesVendaService, Mockito.times(1)).gravaAtualizaInformacoesVenda(Mockito.any());
    }

    @Test
    void deveGravarInformacoesDaVendaQuandoProcessarAutorizacaoEOcorrerErroCausadoPeloParceiroCanal() {
        Mockito.when(parceiroTributarioRepository.obterMatrizTributaria(Mockito.anyInt())).thenReturn(mockTributarioResponse());
        Mockito.when(parceiroSefazRepository.obterAutorizacao(Mockito.any())).thenReturn(new ParceiroSefazResponse());
        Mockito.when(parceiroCanalRepository.confirmarVenda(Mockito.any())).thenThrow(NullPointerException.class);

        service.processarAutorizacaoVenda(mockVendaRequestJson());
        Mockito.verify(parceiroTributarioRepository, Mockito.times(3)).obterMatrizTributaria(Mockito.anyInt());
        Mockito.verify(parceiroSefazRepository, Mockito.times(1)).obterAutorizacao(Mockito.any());
        Mockito.verify(parceiroCanalRepository, Mockito.times(1)).confirmarVenda(Mockito.any());
        Mockito.verify(informacoesVendaService, Mockito.times(1)).gravaAtualizaInformacoesVenda(Mockito.any());
    }

    private String mockVendaRequestJson() {
        return "{\n" +
                "    \"canal\": \"APP\",\n" +
                "    \"empresa\": \"00001\",\n" +
                "    \"loja\": \"0001\",\n" +
                "    \"pdv\": 501,\n" +
                "    \"ordemPedido\": {\n" +
                "        \"numeroPedido\": \"200010710363\",\n" +
                "        \"numeroOrdemExterno\": \"2312529489023-1\",\n" +
                "        \"dataAutorizacao\": \"2022-11-11T15:37:56.194\"\n" +
                "    },\n" +
                "    \"cliente\": {\n" +
                "        \"id\": \"123456\",\n" +
                "        \"nome\": \"Givaldo Santos Vasconcelos\",\n" +
                "        \"documento\": \"70420816097\",\n" +
                "        \"tipoDocumento\": \"CPF\",\n" +
                "        \"tipoPessoa\": \"F\",\n" +
                "        \"endereco\": \"Travessa Francisco Vieira\",\n" +
                "        \"numeroEndereco\": \"11\",\n" +
                "        \"complementoEndereco\": \"Apto 405\",\n" +
                "        \"bairro\": \"Trapiche da Barra\",\n" +
                "        \"cidade\": \"Maceió\",\n" +
                "        \"estado\": \"AL\",\n" +
                "        \"pais\": \"BR\",\n" +
                "        \"cep\": \"57010460\",\n" +
                "        \"codigoIbge\": \"7162435\",\n" +
                "        \"telefone\": \"(82) 36774-7713\",\n" +
                "        \"email\": \"givaldo.santos.vasconcelos@gmail.com\"\n" +
                "    },\n" +
                "    \"totalItens\": 38744,\n" +
                "    \"quantidadeItens\": 6,\n" +
                "    \"itens\": [{\n" +
                "            \"sku\": 547170100,\n" +
                "            \"quantidade\": 3,\n" +
                "            \"valor\": 5691\n" +
                "        }, {\n" +
                "            \"sku\": 557882194,\n" +
                "            \"quantidade\": 2,\n" +
                "            \"valor\": 7990\n" +
                "        }, {\n" +
                "            \"sku\": 557282711,\n" +
                "            \"quantidade\": 1,\n" +
                "            \"valor\": 5691\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    private ParceiroTributarioResponse mockTributarioResponse() {
        return ParceiroTributarioResponse.builder()
                .valorIcms(new BigDecimal("3"))
                .valorPis(new BigDecimal("3"))
                .valorDifaul(new BigDecimal("3"))
                .valorFcpIcms(new BigDecimal("3"))
                .build();
    }
}
