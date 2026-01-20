package com.rafaelcosta.api_gestao_restaurantes.domain.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String usuarioId) {
        super(usuarioId);
    }
}
