package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(
        name = "EnderecoUpdateRequest",
        description = "Dados para atualização parcial do endereço. " +
                "Todos os campos são opcionais; envie apenas os que deseja alterar."
)
public record EnderecoUpdateRequest(

        @Schema(
                description = "Nome da rua.",
                example = "Rua A",
                maxLength = 120,
                nullable = true
        )
        @Size(max = 120)
        String rua,

        @Schema(
                description = "Número do endereço.",
                example = "100",
                maxLength = 20,
                nullable = true
        )
        @Size(max = 20)
        String numero,

        @Schema(
                description = "Complemento do endereço.",
                example = "Apto 12",
                maxLength = 80,
                nullable = true
        )
        @Size(max = 80)
        String complemento,

        @Schema(
                description = "Bairro.",
                example = "Centro",
                maxLength = 80,
                nullable = true
        )
        @Size(max = 80)
        String bairro,

        @Schema(
                description = "Cidade.",
                example = "São Paulo",
                maxLength = 80,
                nullable = true
        )
        @Size(max = 80)
        String cidade,

        @Schema(
                description = "Estado (UF).",
                example = "SP",
                maxLength = 2,
                nullable = true
        )
        @Size(max = 2)
        String estado,

        @Schema(
                description = "CEP.",
                example = "01000-000",
                maxLength = 12,
                nullable = true
        )
        @Size(max = 12)
        String cep

) {}
