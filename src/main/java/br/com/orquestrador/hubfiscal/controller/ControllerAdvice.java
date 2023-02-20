package br.com.orquestrador.hubfiscal.controller;

import br.com.orquestrador.hubfiscal.controller.response.ErroResponse;
import br.com.orquestrador.hubfiscal.exception.ClienteRequestException;
import br.com.orquestrador.hubfiscal.exception.ItemRequestException;
import br.com.orquestrador.hubfiscal.exception.OrdemPedidoRequestException;
import br.com.orquestrador.hubfiscal.exception.VendaRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(VendaRequestException.class)
    public final ResponseEntity<ErroResponse> handleVendaRequestException(VendaRequestException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErroResponse.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(OrdemPedidoRequestException.class)
    public final ResponseEntity<ErroResponse> handleOrdemPedidoRequestException(OrdemPedidoRequestException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErroResponse.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(ClienteRequestException.class)
    public final ResponseEntity<ErroResponse> handleClienteRequestException(ClienteRequestException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErroResponse.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(ItemRequestException.class)
    public final ResponseEntity<ErroResponse> handleItemRequestException(ItemRequestException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseEntity.badRequest()
                .body(ErroResponse.builder()
                        .message(exception.getMessage())
                        .build());
    }
}
