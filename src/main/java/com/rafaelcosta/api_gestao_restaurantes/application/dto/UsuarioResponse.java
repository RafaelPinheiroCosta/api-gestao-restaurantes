package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import com.rafaelcosta.api_gestao_restaurantes.domain.enuns.StatusCadastro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(
        name = "UsuarioResponse",
        description = "Dados completos do usuário retornados pela API."
)
public record UsuarioResponse(

        @Schema(description = "Identificador único do usuário.", format = "uuid",
                example = "11111111-1111-1111-1111-111111111111")
        UUID id,

        @Schema(description = "Nome completo do usuário.", example = "Rafael Costa")
        String nome,

        @Schema(description = "E-mail do usuário.", example = "rafael@email.com", format = "email")
        String email,

        @Schema(description = "Login do usuário.", example = "rafael")
        String login,

        @Schema(description = "Status do cadastro do usuário.", example = "PENDENTE")
        StatusCadastro statusCadastro,

        @Schema(description = "Tipo de perfil do usuário.", allowableValues = {"CLIENTE", "DONO_RESTAURANTE"},
                example = "DONO_RESTAURANTE")
        String perfilTipo,

        @Schema(description = "Endereço do usuário (pode ser nulo caso não cadastrado).", nullable = true)
        EnderecoResponse endereco,

        @Schema(description = "Data/hora de criação.", example = "2026-01-19T20:49:44.237")
        LocalDateTime criadoEm,

        @Schema(description = "Data/hora da última atualização.", example = "2026-01-19T20:49:44.237")
        LocalDateTime atualizadoEm
) {}
