package br.com.orquestrador.hubfiscal.controller.request.parceiros;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ParceiroSefazProductsRequest {
    private Integer sku;
    private Integer amount;
    private BigDecimal value;
    private BigDecimal icmsValue;
    private BigDecimal pisValue;
    private BigDecimal difaulValue;
    private BigDecimal fcpIcmsValue;
}
