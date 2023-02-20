package br.com.orquestrador.hubfiscal.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemTributoDto {
    private Integer sku;
    private Integer quantidade;
    private BigDecimal valor;
    private BigDecimal valorIcms;
    private BigDecimal valorPis;
    private BigDecimal valorDifaul;
    private BigDecimal valorFcpIcms;
}
