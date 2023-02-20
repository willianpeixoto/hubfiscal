package br.com.orquestrador.hubfiscal.controller.request.parceiros;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ParceiroSefazRequest {

    private String orderNumber;
    private String externalOrderNumber;
    private ParceiroSefazCustomerRequest customer;
    private List<ParceiroSefazProductsRequest> products;
}
