package com.marcospinho.demo.resource.exception;

public class CreateOptionalException extends RuntimeException {

    private final String mensagemClient;

    public CreateOptionalException(String mensagemClient, String mensagemException) {
        super(mensagemException);
        this.mensagemClient = mensagemClient;
    }

    public String getMensagemClient() {
        return mensagemClient;
    }
}
