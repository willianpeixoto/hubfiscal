package br.com.orquestrador.hubfiscal.exception;

public class ClienteRequestException extends RuntimeException {

    public ClienteRequestException(final String message) {
        super(message);
    }
}
