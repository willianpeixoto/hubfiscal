package br.com.orquestrador.hubfiscal.controller.request.parceiros.mapper;

import br.com.orquestrador.hubfiscal.controller.request.VendaRequest;
import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroSefazRequest;
import br.com.orquestrador.hubfiscal.dto.ItemTributoDto;

import java.util.List;

import static br.com.orquestrador.hubfiscal.controller.request.parceiros.mapper.ParceiroSefazCustomerRequestMapper.toParceiroSefazCustomerRequest;
import static br.com.orquestrador.hubfiscal.controller.request.parceiros.mapper.ParceiroSefazProductsRequestMapper.toParceiroSefazProductsRequest;

public class ParceiroSefazRequestMapper {

    public static ParceiroSefazRequest toParceiroSefazRequest(VendaRequest venda, List<ItemTributoDto> itensTributos) {
        return ParceiroSefazRequest.builder()
                .orderNumber(venda.getOrdemPedido().getNumeroPedido())
                .externalOrderNumber(venda.getOrdemPedido().getNumeroOrdemExterno())
                .customer(toParceiroSefazCustomerRequest(venda.getCliente()))
                .products(toParceiroSefazProductsRequest(itensTributos))
                .build();
    }
}
