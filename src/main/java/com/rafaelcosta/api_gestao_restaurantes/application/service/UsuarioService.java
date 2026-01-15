package com.rafaelcosta.api_gestao_restaurantes.application.service;

import com.rafaelcosta.api_gestao_restaurantes.application.mapper.UsuarioMapper;
import com.rafaelcosta.api_gestao_restaurantes.domain.entity.Endereco;
import com.rafaelcosta.api_gestao_restaurantes.domain.entity.Usuario;
import com.rafaelcosta.api_gestao_restaurantes.domain.enuns.StatusCadastro;
import com.rafaelcosta.api_gestao_restaurantes.domain.repository.EnderecoRepository;
import com.rafaelcosta.api_gestao_restaurantes.domain.repository.UsuarioRepository;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioResponse> findAllUsuarios(int page, int size) {
        int offset = (page - 1) * size;
        return usuarioRepository.findAll(size, offset).stream()
                .map(usuarioMapper::toResponse)
                .toList();
    }

    public Optional<UsuarioResponse> findById(UUID id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toResponse);
    }

    public UsuarioResponse saveUsuario(UsuarioRequest request) {

        Endereco endereco = null;
        if (request.endereco() != null) {
            endereco = usuarioMapper.toEndereco(request.endereco());
            Long enderecoId = enderecoRepository.save(endereco);
            endereco.setId(enderecoId);
        }

        Usuario usuario = Usuario.builder()
                .id(UUID.randomUUID())
                .nome(request.nome())
                .email(request.email())
                .login(request.login())
                .perfilTipo(request.perfilTipo() != null ? request.perfilTipo() : "CLIENTE")
                .senhaHash(passwordEncoder.encode(request.senha()))
                .statusCadastro(StatusCadastro.PENDENTE)
                .endereco(endereco)
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .build();

        Usuario salvo = usuarioRepository.save(usuario)
                .orElseThrow(() -> new IllegalStateException("Falha ao salvar usuário"));

        return usuarioMapper.toResponse(salvo);
    }

    public Optional<UsuarioResponse> updateUsuario(UUID id, UsuarioRequest request) {

        return usuarioRepository.findById(id).map(atual -> {

            Endereco enderecoAtualizado = atual.getEndereco();

            if (request.endereco() != null) {
                Endereco novoEndereco = usuarioMapper.toEndereco(request.endereco());

                if (enderecoAtualizado == null) {
                    Long novoId = enderecoRepository.save(novoEndereco);
                    novoEndereco.setId(novoId);
                    enderecoAtualizado = novoEndereco;
                } else {
                    enderecoRepository.update(novoEndereco, enderecoAtualizado.getId());
                    novoEndereco.setId(enderecoAtualizado.getId());
                    enderecoAtualizado = novoEndereco;
                }
            }

            String senhaHash = atual.getSenhaHash();
            if (request.senha() != null && !request.senha().isBlank()) {
                senhaHash = passwordEncoder.encode(request.senha());
            }

            Usuario paraSalvar = Usuario.builder()
                    .id(atual.getId())
                    .nome(request.nome() != null ? request.nome() : atual.getNome())
                    .email(request.email() != null ? request.email() : atual.getEmail())
                    .login(request.login() != null ? request.login() : atual.getLogin())
                    .perfilTipo(request.perfilTipo() != null ? request.perfilTipo() : atual.getPerfilTipo())
                    .senhaHash(senhaHash)
                    .statusCadastro(atual.getStatusCadastro())
                    .endereco(enderecoAtualizado)
                    .criadoEm(atual.getCriadoEm())
                    .atualizadoEm(LocalDateTime.now())
                    .build();

            Usuario atualizado = usuarioRepository.update(paraSalvar, id)
                    .orElseThrow(() -> new IllegalStateException("Falha ao atualizar usuário"));

            return usuarioMapper.toResponse(atualizado);
        });
    }

    public void deleteUsuario(UUID id) {
        usuarioRepository.delete(id);
    }
}
