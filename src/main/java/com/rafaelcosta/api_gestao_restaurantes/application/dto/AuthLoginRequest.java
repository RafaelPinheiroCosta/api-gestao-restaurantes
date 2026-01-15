package com.rafaelcosta.api_gestao_restaurantes.application.dto;

public record AuthLoginRequest(
        String login,
        String senha
) {}
