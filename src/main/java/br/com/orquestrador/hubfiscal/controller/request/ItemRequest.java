package br.com.orquestrador.hubfiscal.controller.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemRequest {
    private Integer sku;
    private Integer quantidade;
    private BigDecimal valor;
}
