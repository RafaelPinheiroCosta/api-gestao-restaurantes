package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioChangePasswordRequest(
        @NotBlank String senhaAtual,
        @NotBlank @Size(min = 8, max = 72) String novaSenha
) {}
