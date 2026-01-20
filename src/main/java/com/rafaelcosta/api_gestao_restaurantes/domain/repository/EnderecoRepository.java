package com.rafaelcosta.api_gestao_restaurantes.domain.repository;

import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Endereco;

import java.util.Optional;
import java.util.UUID;

public interface EnderecoRepository {
    Optional<Endereco> findById(Long id);

    Optional<Endereco> findByUsuarioId(UUID usuarioId);

    Long save(UUID usuarioId, Endereco e);

    void update(Endereco e, Long id);
    void delete(Long id);
}
