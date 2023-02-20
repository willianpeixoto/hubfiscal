package br.com.orquestrador.hubfiscal.controller.request.parceiros;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParceiroSefazCustomerRequest {
    private String id;
    private String name;
    private String document;
    private String documentType;
    private String personType;
    private String address;
    private String addressNumber;
    private String addressComplement;
    private String district;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String ibgeCode;
    private String phoneNumber;
    private String email;
}
