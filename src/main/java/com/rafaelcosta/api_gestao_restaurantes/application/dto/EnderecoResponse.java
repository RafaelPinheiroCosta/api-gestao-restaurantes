package com.rafaelcosta.api_gestao_restaurantes.application.dto;


public record EnderecoResponse(
        Long id,
        String rua,
        String numero,
        String complemento,
        String cidade,
        String estado,
        String cep
) {}
