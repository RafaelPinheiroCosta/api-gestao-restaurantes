package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateRequest(
        @Size(min = 3, max = 120) String nome,
        @Email @Size(max = 180) String email,
        @Size(min = 3, max = 60) String login,

        @Pattern(regexp = "CLIENTE|DONO_RESTAURANTE", message = "perfilTipo deve ser CLIENTE ou DONO_RESTAURANTE")
        String perfilTipo,

        @Valid EnderecoRequest endereco
) {}

