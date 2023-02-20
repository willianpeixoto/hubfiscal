package br.com.orquestrador.hubfiscal.controller.request;

import lombok.Data;

import java.util.List;

@Data
public class VendaRequest {
    private String canal;
    private String empresa;
    private String loja;
    private Integer pdv;
    private Integer totalItens;
    private Integer quantidadeItens;
    private OrdemPedidoRequest ordemPedido;
    private ClienteRequest cliente;
    private List<ItemRequest> itens;
}
