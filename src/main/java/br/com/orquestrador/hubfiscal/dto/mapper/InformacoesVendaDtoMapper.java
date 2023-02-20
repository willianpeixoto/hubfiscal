package br.com.orquestrador.hubfiscal.dto.mapper;

import br.com.orquestrador.hubfiscal.controller.request.VendaRequest;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroSefazResponse;
import br.com.orquestrador.hubfiscal.dto.InformacoesVendaDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InformacoesVendaDtoMapper {

    public static InformacoesVendaDto toInformacoesVendaDto(VendaRequest venda,
                                                            String vendaRequest,
                                                            String dtExecucaoSistema) {
        return InformacoesVendaDto.builder()
        .canal(venda.getCanal())
        .codigoEmpresa(Integer.parseInt(venda.getEmpresa()))
        .codigoLoja(Integer.parseInt(venda.getLoja()))
        .numeroPdv(venda.getPdv())
        .numeroPedido(venda.getOrdemPedido().getNumeroPedido())
        .numeroOrdemExterno(venda.getOrdemPedido().getNumeroOrdemExterno())
        .valorTotal(BigDecimal.valueOf(venda.getTotalItens()).divide(new BigDecimal("100")))
        .qtdItem(BigDecimal.valueOf(venda.getQuantidadeItens()))
        .vendaRequest(vendaRequest)
        .dataAtualizacao(LocalDateTime.parse(dtExecucaoSistema))
        .dataRequisicao(LocalDateTime.parse(venda.getOrdemPedido().getDataAutorizacao()))
        .build();
    }

    public static InformacoesVendaDto toInformacoesVendaDtoComSefaz(InformacoesVendaDto informacoesVendaDto,
                                                                    ParceiroSefazResponse sefazResponse) {
        informacoesVendaDto.setChaveNfe(sefazResponse.getNfeKey());
        informacoesVendaDto.setNumeroNota(new BigDecimal(sefazResponse.getInvoiceNumber()));
        informacoesVendaDto.setDataEmissao(LocalDateTime.parse(sefazResponse.getIssuanceDate()));
        informacoesVendaDto.setPdf(sefazResponse.getInvoice());

        return informacoesVendaDto;
    }
}
