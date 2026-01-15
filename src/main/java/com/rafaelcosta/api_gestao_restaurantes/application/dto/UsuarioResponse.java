package com.rafaelcosta.api_gestao_restaurantes.application.dto;

import com.rafaelcosta.api_gestao_restaurantes.domain.enuns.StatusCadastro;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String email,
        String login,
        StatusCadastro statusCadastro,
        String perfilTipo,
        EnderecoResponse endereco,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}
