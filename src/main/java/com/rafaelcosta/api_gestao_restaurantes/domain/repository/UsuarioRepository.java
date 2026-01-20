package com.rafaelcosta.api_gestao_restaurantes.domain.repository;

import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByLogin(String login);

    boolean existsByEmailAndIdNot(String email, UUID id);
    boolean existsByLoginAndIdNot(String login, UUID id);

    void updateSenhaHash(UUID id, String senhaHash);

    List<Usuario> findByNome(String nome, int size, int offset);

    List<Usuario> findAll(int size, int offset);

    Optional<Usuario> findById(UUID id);

    Optional<Usuario> save(Usuario usuario);

    Optional<Usuario> update(Usuario usuario, UUID id);

    Optional<Usuario> findByLogin(String login);

    void delete(UUID id);
}
