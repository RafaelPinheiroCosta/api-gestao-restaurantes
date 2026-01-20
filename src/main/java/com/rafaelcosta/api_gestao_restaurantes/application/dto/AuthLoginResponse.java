package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(
        name = "AuthLoginResponse",
        description = "Resposta retornada após autenticação bem-sucedida. " +
                "O token deve ser enviado no header Authorization como: Bearer <token>."
)
public record AuthLoginResponse(

        @Schema(
                description = "JWT (JSON Web Token) assinado pelo servidor.",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
        )
        String token,

        @Schema(
                description = "Tipo do token a ser usado no header Authorization.",
                example = "Bearer"
        )
        String tokenType,

        @Schema(
                description = "Identificador único (UUID) do usuário autenticado.",
                format = "uuid",
                example = "d290f1ee-6c54-4b01-90e6-d701748f0851"
        )
        UUID usuarioId,

        @Schema(
                description = "Login do usuário autenticado.",
                example = "rafael"
        )
        String login,

        @Schema(
                description = "Autoridade/role do usuário utilizada para autorização nas rotas.",
                example = "DONO_RESTAURANTE"
        )
        String role,

        @Schema(
                description = "Tipo de perfil do usuário no domínio.",
                allowableValues = {"CLIENTE", "DONO_RESTAURANTE"},
                example = "CLIENTE"
        )
        String perfilTipo
) {}
