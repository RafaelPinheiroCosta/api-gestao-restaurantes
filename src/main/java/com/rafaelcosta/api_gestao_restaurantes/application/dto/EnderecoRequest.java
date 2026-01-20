package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
        name = "EnderecoRequest",
        description = "Dados de endereço do usuário (validados na API)."
)
public record EnderecoRequest(
        @NotBlank @Size(max = 120)
        @Schema(description = "Nome da rua/avenida/logradouro.", example = "Rua A", maxLength = 120)
        String rua,

        @NotBlank @Size(max = 20)
        @Schema(description = "Número do imóvel.", example = "100", maxLength = 20)
        String numero,

        @Size(max = 80)
        @Schema(description = "Complemento (opcional).", example = "Apto 12", maxLength = 80, nullable = true)
        String complemento,

        @NotBlank @Size(max = 80)
        @Schema(description = "Bairro.", example = "Centro", maxLength = 80)
        String bairro,

        @NotBlank @Size(max = 80)
        @Schema(description = "Cidade.", example = "São Paulo", maxLength = 80)
        String cidade,

        @NotBlank @Size(max = 2)
        @Schema(description = "UF (sigla do estado).", example = "SP", maxLength = 2, minLength = 2)
        String estado,

        @NotBlank @Size(max = 12)
        @Schema(description = "CEP (apenas dígitos ou com hífen).", example = "01010-000", maxLength = 12)
        String cep

) {}