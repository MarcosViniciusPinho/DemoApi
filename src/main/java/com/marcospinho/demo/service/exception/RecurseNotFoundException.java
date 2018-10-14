package com.marcospinho.demo.service.exception;

public class RecurseNotFoundException extends RuntimeException {

    private String mensagemClient;

    public RecurseNotFoundException(String mensagemClient, String mensagemException) {
        super(mensagemException);
        this.mensagemClient = mensagemClient;
    }

    public String getMensagemClient() {
        return mensagemClient;
    }
}
