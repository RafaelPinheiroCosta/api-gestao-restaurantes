package com.rafaelcosta.api_gestao_restaurantes.infrastructure.repository;

import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Endereco;
import com.rafaelcosta.api_gestao_restaurantes.domain.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

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
    public Optional<Endereco> findByUsuarioId(UUID usuarioId) {
        return jdbcClient.sql("SELECT * FROM enderecos WHERE usuario_id = :usuarioId")
                .param("usuarioId", usuarioId.toString())
                .query(Endereco.class)
                .optional();
    }

    @Override
    public Long save(UUID usuarioId, Endereco e) {

        jdbcClient.sql("""
                INSERT INTO enderecos (usuario_id, rua, numero, complemento, bairro, cidade, estado, cep)
                VALUES (:usuarioId, :rua, :numero, :complemento, :bairro, :cidade, :estado, :cep)
                """)
                .param("usuarioId", usuarioId.toString())
                .param("rua", e.getRua())
                .param("numero", e.getNumero())
                .param("complemento", e.getComplemento())
                .param("bairro", e.getBairro())
                .param("cidade", e.getCidade())
                .param("estado", e.getEstado())
                .param("cep", e.getCep())
                .update();

        Long id = jdbcClient.sql("SELECT id FROM enderecos WHERE usuario_id = :usuarioId")
                .param("usuarioId", usuarioId.toString())
                .query(Long.class)
                .single();

        if (id == null) {
            throw new IllegalStateException("Falha ao obter id do endereço recém-criado para usuario_id=" + usuarioId);
        }
        return id;
    }

    @Override
    public void update(Endereco e, Long id) {
        jdbcClient.sql("""
                UPDATE enderecos
                SET rua = :rua,
                    numero = :numero,
                    complemento = :complemento,
                    bairro = :bairro,
                    cidade = :cidade,
                    estado = :estado,
                    cep = :cep
                WHERE id = :id
                """)
                .param("id", id)
                .param("rua", e.getRua())
                .param("numero", e.getNumero())
                .param("complemento", e.getComplemento())
                .param("bairro", e.getBairro())
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
