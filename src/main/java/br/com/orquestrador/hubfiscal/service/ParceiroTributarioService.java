package br.com.orquestrador.hubfiscal.service;

import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroTributarioResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ParceiroTributarioService {

    private static final Integer SKU_1 = 547170100;
    private static final Integer SKU_2 = 557882194;
    private static final Integer SKU_3 = 557282711;

    public ParceiroTributarioResponse parceiroTributario(Integer sku) {
        if(SKU_1.equals(sku)) {
            return getTributo1();
        }
        if(SKU_2.equals(sku)) {
            return getTributo2();
        }
        if(SKU_3.equals(sku)) {
            return getTributo3();
        }
        return ParceiroTributarioResponse.builder().build();
    }

    private ParceiroTributarioResponse getTributo1() {
        return ParceiroTributarioResponse.builder()
                .sku(547170100)
                .valorIcms(new BigDecimal("78"))
                .valorPis(new BigDecimal("32"))
                .valorDifaul(new BigDecimal("56"))
                .valorFcpIcms(new BigDecimal("35"))
                .build();
    }

    private ParceiroTributarioResponse getTributo2() {
        return ParceiroTributarioResponse.builder()
                .sku(557882194)
                .valorIcms(new BigDecimal("2"))
                .valorPis(new BigDecimal("25"))
                .valorDifaul(new BigDecimal("48"))
                .valorFcpIcms(new BigDecimal("59"))
                .build();
    }

    private ParceiroTributarioResponse getTributo3() {
        return ParceiroTributarioResponse.builder()
                .sku(557282711)
                .valorIcms(new BigDecimal("63"))
                .valorPis(new BigDecimal("9"))
                .valorDifaul(new BigDecimal("80"))
                .valorFcpIcms(new BigDecimal("72"))
                .build();
    }
}
