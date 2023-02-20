package br.com.orquestrador.hubfiscal.controller.api;


import br.com.orquestrador.hubfiscal.controller.request.VendaRequest;
import br.com.orquestrador.hubfiscal.controller.response.ErroResponse;
import br.com.orquestrador.hubfiscal.controller.response.VendaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Hub Fiscal", description = "Orquestrador de vendas")
public interface HubApi {

    @Operation(summary = "Autorizar venda",
    description = "Publica mensagem na fila autorizar-venda-queue",
    tags = {"hub-fiscal"})
    @ApiResponses(value = {
            @ApiResponse(
                responseCode = "201",
                description = "Mensagem publicada com sucesso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendaResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Nenhum dado da venda informado," +
                            " nenhum dado da ordem do pedido informado," +
                            " nenhum dado do cliente informado" +
                            " ou nenhum dado de item informado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponse.class)))})
    ResponseEntity<VendaResponse> autorizarVenda(VendaRequest vendaRequest);
}
