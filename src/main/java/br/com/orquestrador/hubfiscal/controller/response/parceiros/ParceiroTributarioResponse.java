package br.com.orquestrador.hubfiscal.controller.response.parceiros;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ParceiroTributarioResponse {
    private Integer sku;
    private BigDecimal valorIcms;
    private BigDecimal valorPis;
    private BigDecimal valorDifaul;
    private BigDecimal valorFcpIcms;
}
