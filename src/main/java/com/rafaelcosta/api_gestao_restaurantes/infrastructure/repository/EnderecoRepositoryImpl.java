package com.rafaelcosta.api_gestao_restaurantes.infrastructure.repository;

import com.rafaelcosta.api_gestao_restaurantes.domain.entity.Endereco;
import com.rafaelcosta.api_gestao_restaurantes.domain.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EnderecoRepositoryImpl implements EnderecoRepository {

    private final JdbcClient jdbcClient;

    @Override
    public Optional<Endereco> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM enderecos WHERE id = :id")
                .param("id", id)
                .query(Endereco.class)
                .optional();
    }

    @Override
    public Long save(Endereco e) {
        jdbcClient.sql("""
                INSERT INTO enderecos (rua, numero, complemento, cidade, estado, cep)
                VALUES (:rua, :numero, :complemento, :cidade, :estado, :cep)
                """)
                .param("rua", e.getRua())
                .param("numero", e.getNumero())
                .param("complemento", e.getComplemento())
                .param("cidade", e.getCidade())
                .param("estado", e.getEstado())
                .param("cep", e.getCep())
                .update();

        return jdbcClient.sql("SELECT LAST_INSERT_ID()")
                .query(Long.class)
                .single();
    }

    @Override
    public void update(Endereco e, Long id) {
        jdbcClient.sql("""
                UPDATE enderecos
                SET rua = :rua,
                    numero = :numero,
                    complemento = :complemento,
                    cidade = :cidade,
                    estado = :estado,
                    cep = :cep
                WHERE id = :id
                """)
                .param("id", id)
                .param("rua", e.getRua())
                .param("numero", e.getNumero())
                .param("complemento", e.getComplemento())
                .param("cidade", e.getCidade())
                .param("estado", e.getEstado())
                .param("cep", e.getCep())
                .update();
    }

    @Override
    public void delete(Long id) {
        jdbcClient.sql("DELETE FROM enderecos WHERE id = :id")
                .param("id", id)
                .update();
    }
}
