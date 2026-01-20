package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioRequest(
        @Schema(example = "Rafael Costa")
        @NotBlank @Size(min = 3, max = 120)
        String nome,

        @Schema(example = "rafael@email.com")
        @NotBlank @Email @Size(max = 180)
        String email,

        @Schema(example = "rafael")
        @NotBlank @Size(min = 3, max = 60)
        String login,

        @Schema(example = "Senha@1234")
        @NotBlank @Size(min = 8, max = 72)
        String senha,

        @Schema(example = "CLIENTE", allowableValues = {"CLIENTE","DONO_RESTAURANTE"})
        @NotNull
        @Pattern(regexp = "CLIENTE|DONO_RESTAURANTE", message = "perfilTipo deve ser CLIENTE ou DONO_RESTAURANTE")
        String perfilTipo,

        @Valid
        EnderecoRequest endereco
) {}

