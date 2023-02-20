package br.com.orquestrador.hubfiscal.entity.mapper;

import br.com.orquestrador.hubfiscal.dto.InformacoesVendaDto;
import br.com.orquestrador.hubfiscal.entity.InformacoesVenda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class InformacoesVendaMapperTest {

    @Test
    void deveRetornarInformacoesVendaQuandoChamarToInformacoesVenda() {
        InformacoesVenda infoVenda = InformacoesVendaMapper.toInformacoesVenda(mockInformacoesVendaDto());
        Assertions.assertNotNull(infoVenda);
        Assertions.assertEquals(mockInformacoesVendaDto().getCanal(), infoVenda.getCanal());
        Assertions.assertEquals(mockInformacoesVendaDto().getCodigoEmpresa(), infoVenda.getCodigoEmpresa());
        Assertions.assertEquals(mockInformacoesVendaDto().getCodigoLoja(), infoVenda.getCodigoLoja());
        Assertions.assertEquals(mockInformacoesVendaDto().getNumeroPdv(), infoVenda.getNumeroPdv());
        Assertions.assertEquals(mockInformacoesVendaDto().getNumeroPedido(), infoVenda.getNumeroPedido());
        Assertions.assertEquals(mockInformacoesVendaDto().getNumeroOrdemExterno(), infoVenda.getNumeroOrdemExterno());
        Assertions.assertEquals(mockInformacoesVendaDto().getValorTotal(), infoVenda.getValorTotal());
        Assertions.assertEquals(mockInformacoesVendaDto().getQtdItem(), infoVenda.getQtdItem());
        Assertions.assertEquals(mockInformacoesVendaDto().getVendaRequest(), infoVenda.getVendaRequest());
        Assertions.assertEquals(mockInformacoesVendaDto().getDataAtualizacao(), infoVenda.getDataAtualizacao());
        Assertions.assertEquals(mockInformacoesVendaDto().getDataRequisicao(), infoVenda.getDataRequisicao());
        Assertions.assertEquals(mockInformacoesVendaDto().getChaveNfe(), infoVenda.getChaveNfe());
        Assertions.assertEquals(mockInformacoesVendaDto().getNumeroNota(), infoVenda.getNumeroNota());
        Assertions.assertEquals(mockInformacoesVendaDto().getDataEmissao(), infoVenda.getDataEmissao());
        Assertions.assertEquals(mockInformacoesVendaDto().getPdf(), infoVenda.getPdf());
        Assertions.assertEquals(mockInformacoesVendaDto().getSituacao(), infoVenda.getSituacao());
        Assertions.assertEquals(mockInformacoesVendaDto().getMotivo(), infoVenda.getMotivo());
    }

    private InformacoesVendaDto mockInformacoesVendaDto() {
        return InformacoesVendaDto.builder()
                .canal("APP")
                .codigoEmpresa(1)
                .codigoLoja(1)
                .numeroPdv(501)
                .numeroPedido("200010710363")
                .numeroOrdemExterno("2312529489023-1")
                .valorTotal(new BigDecimal("380"))
                .qtdItem(new BigDecimal("8"))
                .vendaRequest("{jsonRequestFake}")
                .dataAtualizacao(LocalDateTime.parse("2022-11-11T15:47:10"))
                .dataRequisicao(LocalDateTime.parse("2022-10-11T15:37:56.194"))
                .chaveNfe("43210392754738001134550040000159551330074448")
                .numeroNota(new BigDecimal("123456789"))
                .dataEmissao(LocalDateTime.parse("2022-11-11T15:50:10.851"))
                .pdf("NDMyMTAzOTI3NTQ3MzgwMDExMzQ1NTAwNDAwMDAxNTk1NTEzMzAwNzQ0NDg=")
                .situacao("PROCESSADO")
                .motivo("Motivo teste")
                .build();
    }
}
