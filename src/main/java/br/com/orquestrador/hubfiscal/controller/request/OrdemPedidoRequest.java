package br.com.orquestrador.hubfiscal.controller.request;

import lombok.Data;

@Data
public class OrdemPedidoRequest {
    private String numeroPedido;
    private String numeroOrdemExterno;
    private String dataAutorizacao;
}
