package com.rafaelcosta.api_gestao_restaurantes.application.service;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioChangePasswordRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioResponse;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioQueryService usuarioQueryService;
    private final UsuarioCommandService usuarioCommandService;
    private final UsuarioPasswordService usuarioPasswordService;

    public List<UsuarioResponse> findAllUsuarios(int page, int size) {
        return usuarioQueryService.findAll(page, size);
    }
    public Optional<UsuarioResponse> findById(UUID id) {
        return usuarioQueryService.findById(id);
    }
    public List<UsuarioResponse> findByNome(String nome, int page, int size) {
        return usuarioQueryService.findByNome(nome, page, size);
    }
    public UsuarioResponse saveUsuario(UsuarioRequest request) {
        return usuarioCommandService.create(request);
    }
    public UsuarioResponse updateUsuarioPerfil(UUID id, UsuarioUpdateRequest request) {
        return usuarioCommandService.updatePerfil(id, request);
    }
    public void changePassword(UUID id, UsuarioChangePasswordRequest request) {
        usuarioPasswordService.changePassword(id, request);
    }
    public void deleteUsuario(UUID id) {
        usuarioCommandService.delete(id);
    }
}
