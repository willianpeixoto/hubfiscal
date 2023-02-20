package br.com.orquestrador.hubfiscal.service;

import br.com.orquestrador.hubfiscal.controller.request.VendaRequest;
import br.com.orquestrador.hubfiscal.controller.response.VendaResponse;
import br.com.orquestrador.hubfiscal.repository.AutorizarVendaRepository;
import br.com.orquestrador.hubfiscal.util.ConversorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static br.com.orquestrador.hubfiscal.util.VendaRequestValidatorUtil.*;

@Service
@RequiredArgsConstructor
public class AutorizarVendaService {

    private final AutorizarVendaRepository autorizarVendaRepository;
    private final String dtExecucaoSistema;

    public VendaResponse autorizarVenda(VendaRequest venda) {
        validaVenda(venda);
        validaOrdemPedido(venda.getOrdemPedido());
        validaCliente(venda.getCliente());
        validaItens(venda.getItens());
        autorizarVendaRepository.autorizaVenda(ConversorUtil.toJson(venda));
        return VendaResponse.builder()
                .status("EM_PROCESSAMENTO")
                .dataResposta(dtExecucaoSistema)
                .build();
    }
}
