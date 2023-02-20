package br.com.orquestrador.hubfiscal.controller.request.parceiros.mapper;

import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroSefazProductsRequest;
import br.com.orquestrador.hubfiscal.dto.ItemTributoDto;

import java.util.ArrayList;
import java.util.List;

public class ParceiroSefazProductsRequestMapper {

    public static ParceiroSefazProductsRequest toParceiroSefazProductsRequest(ItemTributoDto itemTributoDto) {
        return ParceiroSefazProductsRequest.builder()
                .sku(itemTributoDto.getSku())
                .amount(itemTributoDto.getQuantidade())
                .value(itemTributoDto.getValor())
                .icmsValue(itemTributoDto.getValorIcms())
                .pisValue(itemTributoDto.getValorPis())
                .difaulValue(itemTributoDto.getValorDifaul())
                .fcpIcmsValue(itemTributoDto.getValorFcpIcms())
                .build();
    }

    public static List<ParceiroSefazProductsRequest> toParceiroSefazProductsRequest(List<ItemTributoDto> itensTributos) {
        List<ParceiroSefazProductsRequest> parceiroSefazProductsRequestList = new ArrayList<>();
        itensTributos.forEach(i -> parceiroSefazProductsRequestList.add(toParceiroSefazProductsRequest(i)));
        return parceiroSefazProductsRequestList;
    }
}
