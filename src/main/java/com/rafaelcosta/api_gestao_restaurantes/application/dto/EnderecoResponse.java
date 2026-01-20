package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "EnderecoResponse",
        description = "Dados de endereço retornados pela API."
)
public record EnderecoResponse(

        @Schema(description = "Identificador do endereço.", example = "1")
        Long id,

        @Schema(description = "Nome da rua/avenida.", example = "Rua A", maxLength = 120)
        String rua,

        @Schema(description = "Número.", example = "100", maxLength = 20)
        String numero,

        @Schema(description = "Complemento (opcional).", example = "Apto 12", nullable = true, maxLength = 80)
        String complemento,

        @Schema(description = "Bairro.", example = "Centro", maxLength = 80)
        String bairro,

        @Schema(description = "Cidade.", example = "São Paulo", maxLength = 80)
        String cidade,

        @Schema(description = "UF.", example = "SP", maxLength = 2)
        String estado,

        @Schema(description = "CEP.", example = "01010-000", maxLength = 12)
        String cep
) {}
