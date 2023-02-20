package br.com.orquestrador.hubfiscal.controller.api;

import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroCanalRequest;
import br.com.orquestrador.hubfiscal.controller.request.parceiros.ParceiroSefazRequest;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroSefazResponse;
import br.com.orquestrador.hubfiscal.controller.response.parceiros.ParceiroTributarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Parceiros", description = "Parceiros utilizados pelo orquestrador de vendas")
public interface ParceirosApi {

    @Operation(summary = "Tributário",
            description = "Obtem a matriz tributária de todos os produtos",
            tags = {"parceiro-tributario"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Matriz tributária retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParceiroTributarioResponse.class)))})
    ResponseEntity<ParceiroTributarioResponse> parceiroTributario(@PathVariable Integer sku);

    @Operation(summary = "Sefaz",
            description = "Autoriza venda no Sefaz",
            tags = {"parceiro-sefaz"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Autorização de venda retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParceiroSefazResponse.class)))})
    ResponseEntity<ParceiroSefazResponse> parceiroSefaz(@RequestBody ParceiroSefazRequest parceiroSefazRequest);

    @Operation(summary = "Canal",
            description = "Envia informações de confirmação da venda para o canal",
            tags = {"parceiro-canal"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Venda recebida com sucesso pelo canal",
                    content = @Content(
                            mediaType = "*/*",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject("Venda 123123123123-1 recebida com sucesso")))})
    ResponseEntity<String> parceiroCanal(@RequestBody ParceiroCanalRequest parceiroCanalRequest);
}
