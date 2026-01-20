package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoRequest(
        @NotBlank @Size(max = 120)
        String rua,

        @NotBlank @Size(max = 20)
        String numero,

        @Size(max = 80)
        String complemento,

        @NotBlank @Size(max = 80)
        String bairro,

        @NotBlank @Size(max = 80)
        String cidade,

        @NotBlank @Size(max = 2)
        String estado,

        @NotBlank @Size(max = 12)
        String cep

) {}