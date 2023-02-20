package br.com.orquestrador.hubfiscal.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VendaResponse {
    private String status;
    private String dataResposta;
}
