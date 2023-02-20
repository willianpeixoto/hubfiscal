package br.com.orquestrador.hubfiscal.dto.mapper;

import br.com.orquestrador.hubfiscal.controller.request.ItemRequest;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroTributarioResponse;
import br.com.orquestrador.hubfiscal.dto.ItemTributoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ItemTributoDtoMapperTest {

    @Test
    void deveRetornarItemTributoDtoQuandoChamarToItemTributo() {
        ItemTributoDto dto = ItemTributoDtoMapper.toItemTributo(mockItemRequest(), mockTributarioResponse());
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(mockItemRequest().getSku(), dto.getSku());
        Assertions.assertEquals(mockItemRequest().getQuantidade(), dto.getQuantidade());
        Assertions.assertEquals(mockItemRequest().getValor(), dto.getValor());
        Assertions.assertEquals(mockTributarioResponse().getValorIcms(), dto.getValorIcms());
        Assertions.assertEquals(mockTributarioResponse().getValorPis(), dto.getValorPis());
        Assertions.assertEquals(mockTributarioResponse().getValorDifaul(), dto.getValorDifaul());
        Assertions.assertEquals(mockTributarioResponse().getValorFcpIcms(), dto.getValorFcpIcms());
    }

    private ItemRequest mockItemRequest() {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setSku(38);
        itemRequest.setQuantidade(5);
        itemRequest.setValor(new BigDecimal("12"));
        return itemRequest;
    }

    private ParceiroTributarioResponse mockTributarioResponse() {
        return ParceiroTributarioResponse.builder()
                .sku(38)
                .valorIcms(new BigDecimal("3"))
                .valorPis(new BigDecimal("3"))
                .valorDifaul(new BigDecimal("3"))
                .valorFcpIcms(new BigDecimal("3"))
                .build();
    }
}
