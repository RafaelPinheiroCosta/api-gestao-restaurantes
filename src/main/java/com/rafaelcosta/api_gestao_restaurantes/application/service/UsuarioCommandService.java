package com.rafaelcosta.api_gestao_restaurantes.application.service;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioResponse;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioUpdateRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.mapper.UsuarioMapper;
import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Endereco;
import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Usuario;
import com.rafaelcosta.api_gestao_restaurantes.domain.enuns.StatusCadastro;
import com.rafaelcosta.api_gestao_restaurantes.domain.exception.UsuarioNaoEncontradoException;
import com.rafaelcosta.api_gestao_restaurantes.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioCommandService {

    private static final String PERFIL_PADRAO = "CLIENTE";

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    private final UsuarioUniquenessValidator uniquenessValidator;
    private final EnderecoManager enderecoManager;

    @Transactional
    public UsuarioResponse create(UsuarioRequest request) {
        uniquenessValidator.validateForCreate(request.email(), request.login());

        Endereco endereco = enderecoManager.upsertEndereco(null, request.endereco());

        Usuario novo = buildNewUsuario(request, endereco);

        Usuario salvo = usuarioRepository.save(novo)
                .orElseThrow(() -> new IllegalStateException("Falha ao salvar usuário"));

        return usuarioMapper.toResponse(salvo);
    }

    @Transactional
    public UsuarioResponse updatePerfil(UUID id, UsuarioUpdateRequest request) {
        Usuario atual = getUsuarioOrThrow(id);

        uniquenessValidator.validateForUpdate(id, request.email(), request.login());

        Endereco enderecoAtualizado = enderecoManager.upsertEndereco(atual.getEndereco(), request.endereco());

        Usuario paraSalvar = buildUpdatedUsuario(atual, request, enderecoAtualizado);

        Usuario atualizado = usuarioRepository.update(paraSalvar, id)
                .orElseThrow(() -> new IllegalStateException("Falha ao atualizar usuário"));

        return usuarioMapper.toResponse(atualizado);
    }

    @Transactional
    public void delete(UUID id) {
        getUsuarioOrThrow(id);
        usuarioRepository.delete(id);
    }

    private Usuario getUsuarioOrThrow(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id.toString()));
    }

    private Usuario buildNewUsuario(UsuarioRequest request, Endereco endereco) {
        LocalDateTime now = LocalDateTime.now();

        return Usuario.builder()
                .id(UUID.randomUUID())
                .nome(request.nome())
                .email(request.email())
                .login(request.login())
                .perfilTipo(defaultIfNull(request.perfilTipo(), PERFIL_PADRAO))
                .senhaHash(passwordEncoder.encode(request.senha()))
                .statusCadastro(StatusCadastro.PENDENTE)
                .endereco(endereco)
                .criadoEm(now)
                .atualizadoEm(now)
                .build();
    }

    private Usuario buildUpdatedUsuario(Usuario atual, UsuarioUpdateRequest request, Endereco enderecoAtualizado) {
        return Usuario.builder()
                .id(atual.getId())
                .nome(merge(request.nome(), atual.getNome()))
                .email(merge(request.email(), atual.getEmail()))
                .login(merge(request.login(), atual.getLogin()))
                .perfilTipo(merge(request.perfilTipo(), atual.getPerfilTipo()))
                .senhaHash(atual.getSenhaHash())
                .statusCadastro(atual.getStatusCadastro())
                .endereco(enderecoAtualizado)
                .criadoEm(atual.getCriadoEm())
                .atualizadoEm(LocalDateTime.now())
                .build();
    }

    private <T> T merge(T novoValor, T valorAtual) {
        return novoValor != null ? novoValor : valorAtual;
    }

    private <T> T defaultIfNull(T valor, T padrao) {
        return valor != null ? valor : padrao;
    }
}
