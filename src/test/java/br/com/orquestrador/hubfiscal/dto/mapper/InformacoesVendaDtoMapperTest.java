package br.com.orquestrador.hubfiscal.dto.mapper;

import br.com.orquestrador.hubfiscal.controller.request.OrdemPedidoRequest;
import br.com.orquestrador.hubfiscal.controller.request.VendaRequest;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroSefazResponse;
import br.com.orquestrador.hubfiscal.dto.InformacoesVendaDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class InformacoesVendaDtoMapperTest {

    @Test
    void deveRetornarInformacoesVendaDtoQuandoChamarToInformacoesVendaDto() {
        InformacoesVendaDto dto = InformacoesVendaDtoMapper.toInformacoesVendaDto(
                                                                    mockVendaRequest(),
                                                                    mockVendaJson(),
                                                                    mockDtExecucaoSistema());
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(mockVendaRequest().getCanal(), dto.getCanal());
        Assertions.assertEquals(Integer.parseInt(mockVendaRequest().getEmpresa()), dto.getCodigoEmpresa());
        Assertions.assertEquals(Integer.parseInt(mockVendaRequest().getLoja()), dto.getCodigoLoja());
        Assertions.assertEquals(mockVendaRequest().getPdv(), dto.getNumeroPdv());
        Assertions.assertEquals(mockVendaRequest().getOrdemPedido().getNumeroPedido(), dto.getNumeroPedido());
        Assertions.assertEquals(mockVendaRequest().getOrdemPedido().getNumeroOrdemExterno(), dto.getNumeroOrdemExterno());
        Assertions.assertEquals(valorTotalEsperado(mockVendaRequest().getTotalItens()), dto.getValorTotal());
        Assertions.assertEquals(BigDecimal.valueOf(mockVendaRequest().getQuantidadeItens()), dto.getQtdItem());
        Assertions.assertEquals(mockVendaJson(), dto.getVendaRequest());
        Assertions.assertEquals(LocalDateTime.parse(mockDtExecucaoSistema()), dto.getDataAtualizacao());
        Assertions.assertEquals(LocalDateTime.parse(mockVendaRequest().getOrdemPedido().getDataAutorizacao()), dto.getDataRequisicao());
    }

    @Test
    void deveRetornarInformacoesVendaDtoQuandoChamarToInformacoesVendaDtoComSefaz() {
        InformacoesVendaDto dto = InformacoesVendaDtoMapper.toInformacoesVendaDtoComSefaz(InformacoesVendaDto.builder().build(), mockSefaz());
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(mockSefaz().getNfeKey(), dto.getChaveNfe());
        Assertions.assertEquals(new BigDecimal(mockSefaz().getInvoiceNumber()), dto.getNumeroNota());
        Assertions.assertEquals(LocalDateTime.parse(mockSefaz().getIssuanceDate()), dto.getDataEmissao());
        Assertions.assertEquals(mockSefaz().getInvoice(), dto.getPdf());
    }

    private VendaRequest mockVendaRequest() {
        VendaRequest vendaRequest = new VendaRequest();
        vendaRequest.setCanal("APP");
        vendaRequest.setEmpresa("00001");
        vendaRequest.setLoja("0001");
        vendaRequest.setPdv(501);
        vendaRequest.setTotalItens(501);
        vendaRequest.setQuantidadeItens(501);
        vendaRequest.setOrdemPedido(mockOrdemPedido());
        return vendaRequest;
    }

    private OrdemPedidoRequest mockOrdemPedido() {
        OrdemPedidoRequest ordemPedidoRequest = new OrdemPedidoRequest();
        ordemPedidoRequest.setNumeroPedido("123456");
        ordemPedidoRequest.setNumeroOrdemExterno("123456");
        ordemPedidoRequest.setDataAutorizacao("2022-10-11T15:37:56.194");
        return ordemPedidoRequest;
    }

    private String mockVendaJson() {
        return "{vendaJsonFake}";
    }

    private String mockDtExecucaoSistema() {
        return "2022-11-11T15:47:10";
    }

    private ParceiroSefazResponse mockSefaz() {
        return new ParceiroSefazResponse();
    }

    private BigDecimal valorTotalEsperado(Integer valorTotal) {
        return BigDecimal.valueOf(valorTotal).divide(new BigDecimal("100"));
    }
}
