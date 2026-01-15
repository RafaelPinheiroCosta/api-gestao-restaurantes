package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import java.util.UUID;

public record AuthLoginResponse(
        String token,
        String tokenType,
        UUID usuarioId,
        String login,
        String role,
        String perfilTipo
) {}
