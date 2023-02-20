package br.com.orquestrador.hubfiscal.exception;

public class OrdemPedidoRequestException extends RuntimeException {

    public OrdemPedidoRequestException(final String message) {
        super(message);
    }
}