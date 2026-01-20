package com.rafaelcosta.api_gestao_restaurantes.domain.exception;

public class SenhaAtualInvalidaException extends RuntimeException {
    public SenhaAtualInvalidaException() {
        super("message");
    }
}
