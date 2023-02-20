package br.com.orquestrador.hubfiscal.entity.mapper;

import br.com.orquestrador.hubfiscal.dto.InformacoesVendaDto;
import br.com.orquestrador.hubfiscal.entity.InformacoesVenda;

public class InformacoesVendaMapper {

    public static InformacoesVenda toInformacoesVenda(InformacoesVendaDto informacoesVendaDto) {
        InformacoesVenda informacoesVenda = new InformacoesVenda();
        informacoesVenda.setCanal(informacoesVendaDto.getCanal());
        informacoesVenda.setCodigoEmpresa(informacoesVendaDto.getCodigoEmpresa());
        informacoesVenda.setCodigoLoja(informacoesVendaDto.getCodigoLoja());
        informacoesVenda.setNumeroPdv(informacoesVendaDto.getNumeroPdv());
        informacoesVenda.setNumeroPedido(informacoesVendaDto.getNumeroPedido());
        informacoesVenda.setNumeroOrdemExterno(informacoesVendaDto.getNumeroOrdemExterno());
        informacoesVenda.setValorTotal(informacoesVendaDto.getValorTotal());
        informacoesVenda.setQtdItem(informacoesVendaDto.getQtdItem());
        informacoesVenda.setVendaRequest(informacoesVendaDto.getVendaRequest());
        informacoesVenda.setDataAtualizacao(informacoesVendaDto.getDataAtualizacao());
        informacoesVenda.setDataRequisicao(informacoesVendaDto.getDataRequisicao());
        informacoesVenda.setChaveNfe(informacoesVendaDto.getChaveNfe());
        informacoesVenda.setNumeroNota(informacoesVendaDto.getNumeroNota());
        informacoesVenda.setDataEmissao(informacoesVendaDto.getDataEmissao());
        informacoesVenda.setPdf(informacoesVendaDto.getPdf());
        informacoesVenda.setSituacao(informacoesVendaDto.getSituacao());
        informacoesVenda.setMotivo(informacoesVendaDto.getMotivo());
        return informacoesVenda;
    }
}
