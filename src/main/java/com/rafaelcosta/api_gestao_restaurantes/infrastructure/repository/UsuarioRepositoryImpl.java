package com.rafaelcosta.api_gestao_restaurantes.infrastructure.repository;

import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Endereco;
import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Usuario;
import com.rafaelcosta.api_gestao_restaurantes.domain.enuns.StatusCadastro;
import com.rafaelcosta.api_gestao_restaurantes.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final JdbcClient jdbcClient;

    private static final RowMapper<Usuario> USUARIO_MAPPER = (rs, rowNum) -> {

        Endereco endereco = null;
        Long enderecoId = rs.getLong("e_id");
        if (!rs.wasNull()) {
            endereco = Endereco.builder()
                    .id(enderecoId)
                    .rua(rs.getString("e_rua"))
                    .numero(rs.getString("e_numero"))
                    .complemento(rs.getString("e_complemento"))
                    .bairro(rs.getString("e_bairro"))
                    .cidade(rs.getString("e_cidade"))
                    .estado(rs.getString("e_estado"))
                    .cep(rs.getString("e_cep"))
                    .build();
        }

        Usuario usuario = Usuario.builder()
                .id(UUID.fromString(rs.getString("u_id")))
                .nome(rs.getString("u_nome"))
                .email(rs.getString("u_email"))
                .login(rs.getString("u_login"))
                .senhaHash(rs.getString("u_senha_hash"))
                .statusCadastro(StatusCadastro.valueOf(rs.getString("u_status_cadastro")))
                .perfilTipo(rs.getString("u_perfil_tipo"))
                .endereco(endereco)
                .criadoEm(rs.getTimestamp("u_criado_em").toLocalDateTime())
                .atualizadoEm(rs.getTimestamp("u_atualizado_em").toLocalDateTime())
                .build();

        usuario.carregarPerfil();
        return usuario;
    };

    private static final String BASE_SELECT = """
        SELECT
            u.id              AS u_id,
            u.nome            AS u_nome,
            u.email           AS u_email,
            u.login           AS u_login,
            u.perfil_tipo     AS u_perfil_tipo,
            u.senha_hash      AS u_senha_hash,
            u.status_cadastro AS u_status_cadastro,
            u.criado_em       AS u_criado_em,
            u.atualizado_em   AS u_atualizado_em,

            e.id              AS e_id,
            e.rua             AS e_rua,
            e.numero          AS e_numero,
            e.complemento     AS e_complemento,
            e.bairro          AS e_bairro,
            e.cidade          AS e_cidade,
            e.estado          AS e_estado,
            e.cep             AS e_cep
        FROM usuarios u
        LEFT JOIN enderecos e ON e.usuario_id = u.id
        """;

    @Override
    public Optional<Usuario> findById(UUID id) {
        return jdbcClient.sql(BASE_SELECT + " WHERE u.id = :id")
                .param("id", id.toString())
                .query(USUARIO_MAPPER)
                .optional();
    }

    @Override
    public List<Usuario> findAll(int size, int offset) {
        return jdbcClient.sql(
                        BASE_SELECT + " ORDER BY u.criado_em DESC LIMIT :size OFFSET :offset"
                )
                .param("size", size)
                .param("offset", offset)
                .query(USUARIO_MAPPER)
                .list();
    }

    @Override
    public Optional<Usuario> findByLogin(String login) {
        return jdbcClient.sql(BASE_SELECT + " WHERE u.login = :login")
                .param("login", login)
                .query(USUARIO_MAPPER)
                .optional();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jdbcClient.sql(BASE_SELECT + " WHERE LOWER(u.email) = LOWER(:email)")
                .param("email", email)
                .query(USUARIO_MAPPER)
                .optional();
    }

    @Override
    public boolean existsByEmail(String email) {
        Integer count = jdbcClient.sql("SELECT COUNT(1) FROM usuarios WHERE LOWER(email) = LOWER(:email)")
                .param("email", email)
                .query(Integer.class)
                .single();
        return count != null && count > 0;
    }

    @Override
    public boolean existsByLogin(String login) {
        Integer count = jdbcClient.sql("SELECT COUNT(1) FROM usuarios WHERE LOWER(login) = LOWER(:login)")
                .param("login", login)
                .query(Integer.class)
                .single();
        return count != null && count > 0;
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, UUID id) {
        Integer count = jdbcClient.sql("""
                        SELECT COUNT(1)
                        FROM usuarios
                        WHERE LOWER(email) = LOWER(:email)
                          AND id <> :id
                        """)
                .param("email", email)
                .param("id", id.toString())
                .query(Integer.class)
                .single();
        return count != null && count > 0;
    }

    @Override
    public boolean existsByLoginAndIdNot(String login, UUID id) {
        Integer count = jdbcClient.sql("""
                        SELECT COUNT(1)
                        FROM usuarios
                        WHERE LOWER(login) = LOWER(:login)
                          AND id <> :id
                        """)
                .param("login", login)
                .param("id", id.toString())
                .query(Integer.class)
                .single();
        return count != null && count > 0;
    }

    @Override
    public List<Usuario> findByNome(String nome, int size, int offset) {
        return jdbcClient.sql(BASE_SELECT + """
            WHERE LOWER(u.nome) LIKE LOWER(:nome)
            ORDER BY u.criado_em DESC
            LIMIT :size OFFSET :offset
            """)
                .param("nome", "%" + nome + "%")
                .param("size", size)
                .param("offset", offset)
                .query(USUARIO_MAPPER)
                .list();
    }

    @Override
    public void updateSenhaHash(UUID id, String senhaHash) {
        jdbcClient.sql("""
            UPDATE usuarios
            SET senha_hash = :senhaHash,
                atualizado_em = :atualizadoEm
            WHERE id = :id
            """)
                .param("id", id.toString())
                .param("senhaHash", senhaHash)
                .param("atualizadoEm", LocalDateTime.now())
                .update();
    }

    @Override
    public Optional<Usuario> save(Usuario usuario) {
        UUID id = usuario.getId() != null ? usuario.getId() : UUID.randomUUID();

        jdbcClient.sql("""
                        INSERT INTO usuarios (
                            id, nome, email, login, senha_hash, status_cadastro,
                            perfil_tipo, criado_em, atualizado_em
                        )
                        VALUES (
                            :id, :nome, :email, :login, :senhaHash, :statusCadastro,
                            :perfilTipo, :criadoEm, :atualizadoEm
                        )
                """)
                .param("id", id.toString())
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("login", usuario.getLogin())
                .param("perfilTipo", usuario.getPerfilTipo())
                .param("senhaHash", usuario.getSenhaHash())
                .param("statusCadastro", usuario.getStatusCadastro().name())
                .param("criadoEm", usuario.getCriadoEm() != null ? usuario.getCriadoEm() : LocalDateTime.now())
                .param("atualizadoEm", usuario.getAtualizadoEm() != null ? usuario.getAtualizadoEm() : LocalDateTime.now())
                .update();

        return findById(id);
    }

    @Override
    public Optional<Usuario> update(Usuario usuario, UUID id) {
        jdbcClient.sql("""
                UPDATE usuarios
                SET nome = :nome,
                    email = :email,
                    login = :login,
                    perfil_tipo = :perfilTipo,
                    senha_hash = :senhaHash,
                    status_cadastro = :statusCadastro,
                    atualizado_em = :atualizadoEm
                WHERE id = :id
                """)
                .param("id", id.toString())
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("login", usuario.getLogin())
                .param("perfilTipo", usuario.getPerfilTipo())
                .param("senhaHash", usuario.getSenhaHash())
                .param("statusCadastro", usuario.getStatusCadastro().name())
                .param("atualizadoEm", LocalDateTime.now())
                .update();

        return findById(id);
    }

    @Override
    public void delete(UUID id) {
        jdbcClient.sql("DELETE FROM usuarios WHERE id = :id")
                .param("id", id.toString())
                .update();
    }
}
