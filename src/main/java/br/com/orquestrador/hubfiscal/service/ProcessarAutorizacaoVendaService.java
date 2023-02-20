package br.com.orquestrador.hubfiscal.service;

import br.com.orquestrador.hubfiscal.controller.request.ItemRequest;
import br.com.orquestrador.hubfiscal.controller.request.VendaRequest;
import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroCanalRequest;
import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroSefazRequest;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroSefazResponse;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroTributarioResponse;
import br.com.orquestrador.hubfiscal.dto.InformacoesVendaDto;
import br.com.orquestrador.hubfiscal.dto.ItemTributoDto;
import br.com.orquestrador.hubfiscal.repository.ParceiroCanalRepository;
import br.com.orquestrador.hubfiscal.repository.ParceiroSefazRepository;
import br.com.orquestrador.hubfiscal.repository.ParceiroTributarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.orquestrador.hubfiscal.controller.request.parceiros.mapper.ParceiroSefazRequestMapper.toParceiroSefazRequest;
import static br.com.orquestrador.hubfiscal.controller.request.parceiros.mapper.ParceiroCanalRequestMapper.toParceiroCanalRequest;
import static br.com.orquestrador.hubfiscal.dto.mapper.InformacoesVendaDtoMapper.toInformacoesVendaDto;
import static br.com.orquestrador.hubfiscal.dto.mapper.InformacoesVendaDtoMapper.toInformacoesVendaDtoComSefaz;
import static br.com.orquestrador.hubfiscal.dto.mapper.ItemTributoDtoMapper.toItemTributo;
import static br.com.orquestrador.hubfiscal.entity.mapper.InformacoesVendaMapper.toInformacoesVenda;
import static br.com.orquestrador.hubfiscal.util.ConversorUtil.toObject;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessarAutorizacaoVendaService {

    private final ParceiroTributarioRepository parceiroTributarioRepository;
    private final ParceiroSefazRepository parceiroSefazRepository;
    private final ParceiroCanalRepository parceiroCanalRepository;
    private final InformacoesVendaService informacoesVendaService;
    private final String dtExecucaoSistema;

    public void processarAutorizacaoVenda(String messagem) {
        VendaRequest venda = getVenda(messagem);
        InformacoesVendaDto informacoesVendaDto = preencherInformacoesVenda(messagem, venda);
        try {
            informacoesVendaDto = processaAutorizacaoVenda(venda, informacoesVendaDto);
        } catch (Exception e) {
            informacoesVendaDto.setSituacao("ERRO");
            log.error("Ocorreu erro durante o processamento da venda {}\n{}", messagem, e);
        }
        informacoesVendaService.gravaAtualizaInformacoesVenda(toInformacoesVenda(informacoesVendaDto));
        log.debug("Gravou os dados da venda processada com sucesso no banco");
    }

    private VendaRequest getVenda(String messagem) {
        return (VendaRequest) toObject(messagem, VendaRequest.class);
    }

    private InformacoesVendaDto preencherInformacoesVenda(String messagem, VendaRequest venda) {
        InformacoesVendaDto informacoesVendaDto = toInformacoesVendaDto(venda, messagem, dtExecucaoSistema);
        return informacoesVendaDto;
    }

    private InformacoesVendaDto processaAutorizacaoVenda(VendaRequest venda, InformacoesVendaDto informacoesVendaDto) {
        List<ItemTributoDto> itemTributoDtos = obterMatrizTributaria(venda.getItens());
        ParceiroSefazResponse sefazResponse = obterAutorizacaoSefaz(venda, itemTributoDtos);
        informacoesVendaDto = preencherDadosSefazInformacoesVenda(informacoesVendaDto, sefazResponse);
        String respostaCanal = confirmarVenda(
                                    venda.getOrdemPedido().getNumeroPedido(),
                                    venda.getOrdemPedido().getNumeroOrdemExterno(),
                                    sefazResponse);
        informacoesVendaDto = preencherSituacaoMotivoInformacoesVenda(informacoesVendaDto, respostaCanal);
        return informacoesVendaDto;
    }

    private List<ItemTributoDto> obterMatrizTributaria(List<ItemRequest> itens) {
        List<ItemTributoDto> tributos = new ArrayList<>();
        for(ItemRequest item : itens) {
            ParceiroTributarioResponse tributarioResponse = parceiroTributarioRepository.obterMatrizTributaria(item.getSku());
            tributos.add(toItemTributo(item, tributarioResponse));
        }
        return tributos;
    }

    private ParceiroSefazResponse obterAutorizacaoSefaz(VendaRequest venda, List<ItemTributoDto> itensTributos) {
        ParceiroSefazRequest parceiroSefazRequest = toParceiroSefazRequest(venda, itensTributos);
        return parceiroSefazRepository.obterAutorizacao(parceiroSefazRequest);
    }

    private InformacoesVendaDto preencherDadosSefazInformacoesVenda(InformacoesVendaDto informacoesVendaDto,
                                                                    ParceiroSefazResponse sefazResponse) {
        return toInformacoesVendaDtoComSefaz(informacoesVendaDto, sefazResponse);
    }

    private String confirmarVenda(String numeroPedido, String numeroOrdemExterno, ParceiroSefazResponse parceiroSefazResponse) {
        ParceiroCanalRequest parceiroCanalRequest = toParceiroCanalRequest(numeroPedido, numeroOrdemExterno, parceiroSefazResponse);
        return parceiroCanalRepository.confirmarVenda(parceiroCanalRequest);
    }

    private InformacoesVendaDto preencherSituacaoMotivoInformacoesVenda(InformacoesVendaDto informacoesVendaDto, String respostaCanal) {
        if(StringUtils.isNotEmpty(respostaCanal)) {
            informacoesVendaDto.setSituacao("PROCESSADO");
            informacoesVendaDto.setMotivo(respostaCanal);
            return informacoesVendaDto;
        }
        informacoesVendaDto.setSituacao("ERRO");
        return informacoesVendaDto;
    }
}
