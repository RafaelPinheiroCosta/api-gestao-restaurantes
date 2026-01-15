package com.rafaelcosta.api_gestao_restaurantes.domain.repository;

import com.rafaelcosta.api_gestao_restaurantes.domain.entity.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {

    List<Usuario> findAll(int size, int offset);

    Optional<Usuario> findById(UUID id);

    Optional<Usuario> save(Usuario usuario);

    Optional<Usuario> update(Usuario usuario, UUID id);

    void delete(UUID id);
}
