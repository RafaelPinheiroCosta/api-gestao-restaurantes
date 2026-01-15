package com.rafaelcosta.api_gestao_restaurantes.application.dto;


public record UsuarioRequest(
        String nome,
        String email,
        String login,
        String senha,
        EnderecoRequest endereco
) {}
