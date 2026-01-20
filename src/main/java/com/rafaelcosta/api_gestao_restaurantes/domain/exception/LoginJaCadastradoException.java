package com.rafaelcosta.api_gestao_restaurantes.domain.exception;

public class LoginJaCadastradoException extends RuntimeException {
    public LoginJaCadastradoException(String login) {

        super("Já existe um usuário cadastrado com o login: " + login);
    }
}
