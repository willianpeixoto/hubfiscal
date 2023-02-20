package br.com.orquestrador.hubfiscal.controller.request.parceiros.mapper;

import br.com.orquestrador.hubfiscal.controller.request.ClienteRequest;
import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroSefazCustomerRequest;

public class ParceiroSefazCustomerRequestMapper {

    public static ParceiroSefazCustomerRequest toParceiroSefazCustomerRequest(ClienteRequest cliente) {
        return ParceiroSefazCustomerRequest.builder()
                .id(cliente.getId())
                .name(cliente.getNome())
                .document(cliente.getDocumento())
                .documentType(cliente.getTipoDocumento())
                .personType(cliente.getTipoPessoa())
                .address(cliente.getEndereco())
                .addressNumber(cliente.getNumeroEndereco())
                .addressComplement(cliente.getComplementoEndereco())
                .district(cliente.getBairro())
                .city(cliente.getCidade())
                .state(cliente.getEstado())
                .country(cliente.getPais())
                .zipCode(cliente.getCep())
                .ibgeCode(cliente.getCodigoIbge())
                .phoneNumber(cliente.getTelefone())
                .email(cliente.getEmail())
                .build();
    }
}
