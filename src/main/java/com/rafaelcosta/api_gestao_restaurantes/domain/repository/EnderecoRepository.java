package com.rafaelcosta.api_gestao_restaurantes.domain.repository;

import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Endereco;
import java.util.Optional;

public interface EnderecoRepository {
    Optional<Endereco> findById(Long id);
    Long save(Endereco endereco);
    void update(Endereco endereco, Long id);
    void delete(Long id);
}
