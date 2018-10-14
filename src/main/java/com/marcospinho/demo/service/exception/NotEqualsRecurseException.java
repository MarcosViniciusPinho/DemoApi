package com.marcospinho.demo.service.exception;

public class NotEqualsRecurseException extends RuntimeException {

    private String mensagemClient;

    public NotEqualsRecurseException(String mensagemClient, String mensagemException) {
        super(mensagemException);
        this.mensagemClient = mensagemClient;
    }

    public String getMensagemClient() {
        return mensagemClient;
    }
}
