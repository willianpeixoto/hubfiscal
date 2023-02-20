package br.com.orquestrador.hubfiscal.controller;

import br.com.orquestrador.hubfiscal.controller.api.ParceirosApi;
import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroCanalRequest;
import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroSefazRequest;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroSefazResponse;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroTributarioResponse;
import br.com.orquestrador.hubfiscal.service.ParceiroTributarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ParceirosController implements ParceirosApi {

    private final ParceiroTributarioService parceiroTributarioService;

    @GetMapping("/tributo/{sku}")
    public ResponseEntity<ParceiroTributarioResponse> parceiroTributario(@PathVariable Integer sku) {
        ParceiroTributarioResponse parceiroTributarioResponse = parceiroTributarioService.parceiroTributario(sku);
        return new ResponseEntity(parceiroTributarioResponse, HttpStatus.OK);
    }

    @PostMapping("/authorize")
    public ResponseEntity<ParceiroSefazResponse> parceiroSefaz(@RequestBody ParceiroSefazRequest parceiroSefazRequest) {
        return new ResponseEntity(new ParceiroSefazResponse(), HttpStatus.OK);
    }

    @PostMapping("/callback-venda")
    public ResponseEntity<String> parceiroCanal(@RequestBody ParceiroCanalRequest parceiroCanalRequest) {
        String response = "Venda " + parceiroCanalRequest.getNumeroOrdemExterno() + " recebida com sucesso";
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
