package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record AuthLoginRequest(
        @NotNull
        @Schema(example = "dono")
        String login,

        @NotNull
        @Schema(example = "Senha@1234")
        String senha
) {}

