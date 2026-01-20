package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
        name = "UsuarioChangePasswordRequest",
        description = "Requisição para troca de senha do usuário."
)
public record UsuarioChangePasswordRequest(

        @Schema(description = "Senha atual do usuário.", example = "Senha@123")
        @NotBlank String senhaAtual,

        @Schema(description = "Nova senha do usuário.", example = "NovaSenha@456")
        @NotBlank @Size(min = 8, max = 72) String novaSenha
) {}
