package br.com.orquestrador.hubfiscal.controller.request.parceiros;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParceiroCanalRequest {
    private String numeroPedido;
    private String numeroOrdemExterno;
    private String chaveNFE;
    private String numeroNota;
    private String dataEmissao;
    private String pdf;
    private String status;
}
