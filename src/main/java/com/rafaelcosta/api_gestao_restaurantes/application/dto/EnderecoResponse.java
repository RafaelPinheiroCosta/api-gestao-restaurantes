package com.rafaelcosta.api_gestao_restaurantes.application.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoResponse(
        Long id,
        String rua,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado,
        String cep
) {}
