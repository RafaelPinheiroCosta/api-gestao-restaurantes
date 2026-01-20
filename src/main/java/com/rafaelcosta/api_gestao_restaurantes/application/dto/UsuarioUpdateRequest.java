package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "UsuarioUpdateRequest",
        description = "Dados para atualização parcial do usuário. Todos os campos são opcionais."
)
public record UsuarioUpdateRequest(

        @Schema(example = "Rafael Costa", nullable = true)
        @Size(min = 3, max = 120)
        String nome,

        @Schema(example = "rafael@email.com", format = "email", nullable = true)
        @Email
        @Size(max = 180)
        String email,

        @Schema(example = "rafael", nullable = true)
        @Size(min = 3, max = 60)
        String login,

        @Schema(
                example = "DONO_RESTAURANTE",
                allowableValues = {"CLIENTE", "DONO_RESTAURANTE"},
                nullable = true
        )
        @Pattern(regexp = "CLIENTE|DONO_RESTAURANTE")
        String perfilTipo,

        @Schema(nullable = true)
        @Valid
        EnderecoUpdateRequest endereco

) {}


