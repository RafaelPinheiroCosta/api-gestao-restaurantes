package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Schema(
        name = "UsuarioRequest",
        description = "Requisição para cadastro de usuário. Campos obrigatórios e validados pela API."
)
public record UsuarioRequest(

        @Schema(
                description = "Nome completo do usuário.",
                example = "Rafael Costa",
                minLength = 3,
                maxLength = 120
        )
        @NotBlank @Size(min = 3, max = 120)
        String nome,

        @Schema(
                description = "E-mail do usuário.",
                example = "rafael@email.com",
                format = "email",
                maxLength = 180
        )
        @NotBlank @Email @Size(max = 180)
        String email,

        @Schema(
                description = "Login único do usuário.",
                example = "rafael",
                minLength = 3,
                maxLength = 60
        )
        @NotBlank @Size(min = 3, max = 60)
        String login,

        @Schema(
                description = "Senha em texto puro para cadastro (será armazenada como hash).",
                example = "Senha@123",
                minLength = 8,
                maxLength = 72
        )
        @NotBlank @Size(min = 8, max = 72)
        String senha,

        @Schema(
                description = "Tipo de perfil do usuário no sistema.",
                example = "DONO_RESTAURANTE",
                allowableValues = {"CLIENTE", "DONO_RESTAURANTE"}
        )
        @NotBlank
        @Pattern(regexp = "CLIENTE|DONO_RESTAURANTE", message = "perfilTipo deve ser CLIENTE ou DONO_RESTAURANTE")
        String perfilTipo,

        @Schema(
                description = "Endereço do usuário (obrigatório no cadastro se sua regra exigir; opcional caso contrário).",
                nullable = true
        )
        @Valid
        EnderecoRequest endereco

) {}
