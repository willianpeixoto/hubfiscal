package br.com.orquestrador.hubfiscal.controller.request.parceiros.mapper;

import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroCanalRequest;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroSefazResponse;

public class ParceiroCanalRequestMapper {

    public static ParceiroCanalRequest toParceiroCanalRequest(String numeroPedido, String numeroOrdemExterno, ParceiroSefazResponse sefazResponse) {
        return ParceiroCanalRequest.builder()
                .numeroPedido(numeroPedido)
                .numeroOrdemExterno(numeroOrdemExterno)
                .chaveNFE(sefazResponse.getNfeKey())
                .numeroNota(sefazResponse.getInvoiceNumber())
                .dataEmissao(sefazResponse.getIssuanceDate())
                .pdf(sefazResponse.getInvoice())
                .status("PROCESSADO")
                .build();
    }
}
