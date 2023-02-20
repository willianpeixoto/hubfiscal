package br.com.orquestrador.hubfiscal.controller;

import br.com.orquestrador.hubfiscal.controller.api.HubApi;
import br.com.orquestrador.hubfiscal.controller.request.VendaRequest;
import br.com.orquestrador.hubfiscal.controller.response.VendaResponse;
import br.com.orquestrador.hubfiscal.service.AutorizarVendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AutorizarVendaController implements HubApi {

    private final AutorizarVendaService autorizarVendaService;

    @PostMapping("/autorizar-venda")
    public ResponseEntity<VendaResponse> autorizarVenda(@RequestBody VendaRequest vendaRequest) {
        VendaResponse vendaResponse = autorizarVendaService.autorizarVenda(vendaRequest);
        return new ResponseEntity(vendaResponse, HttpStatus.CREATED);
    }
}
