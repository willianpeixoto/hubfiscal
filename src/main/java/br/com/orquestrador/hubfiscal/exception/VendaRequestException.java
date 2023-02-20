package br.com.orquestrador.hubfiscal.exception;

public class VendaRequestException extends RuntimeException {

    public VendaRequestException(final String message) {
        super(message);
    }
}