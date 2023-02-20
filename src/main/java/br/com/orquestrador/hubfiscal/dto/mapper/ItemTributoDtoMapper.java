package br.com.orquestrador.hubfiscal.dto.mapper;

import br.com.orquestrador.hubfiscal.controller.request.ItemRequest;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroTributarioResponse;
import br.com.orquestrador.hubfiscal.dto.ItemTributoDto;

public class ItemTributoDtoMapper {

    public static ItemTributoDto toItemTributo(ItemRequest item, ParceiroTributarioResponse tributo) {
        return ItemTributoDto.builder()
                .sku(item.getSku())
                .quantidade(item.getQuantidade())
                .valor(item.getValor())
                .valorIcms(tributo.getValorIcms())
                .valorPis(tributo.getValorPis())
                .valorDifaul(tributo.getValorDifaul())
                .valorFcpIcms(tributo.getValorFcpIcms())
                .build();
    }
}
