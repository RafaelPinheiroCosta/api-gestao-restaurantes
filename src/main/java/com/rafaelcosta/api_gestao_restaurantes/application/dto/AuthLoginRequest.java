package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
        name = "AuthLoginRequest",
        description = "Requisição de autenticação (login e senha) para emissão de JWT."
)
public record AuthLoginRequest(

        @Schema(
                description = "Login do usuário.",
                example = "dono",
                minLength = 3,
                maxLength = 60
        )
        @NotBlank
        @Size(min = 3, max = 60)
        String login,

        @Schema(
                description = "Senha do usuário.",
                example = "Senha@123",
                minLength = 8,
                maxLength = 72
        )
        @NotBlank
        @Size(min = 8, max = 72)
        String senha

) {}
