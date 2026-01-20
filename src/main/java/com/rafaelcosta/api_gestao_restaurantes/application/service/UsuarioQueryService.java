package com.rafaelcosta.api_gestao_restaurantes.application.service;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioResponse;
import com.rafaelcosta.api_gestao_restaurantes.application.mapper.UsuarioMapper;
import com.rafaelcosta.api_gestao_restaurantes.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioQueryService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PaginationCalculator pagination;

    public List<UsuarioResponse> findAll(int page, int size) {
        int safeSize = pagination.size(size);
        int offset = pagination.offset(page, safeSize);

        return usuarioRepository.findAll(safeSize, offset).stream()
                .map(usuarioMapper::toResponse)
                .toList();
    }

    public Optional<UsuarioResponse> findById(UUID id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toResponse);
    }

    public List<UsuarioResponse> findByNome(String nome, int page, int size) {
        int safeSize = pagination.size(size);
        int offset = pagination.offset(page, safeSize);

        return usuarioRepository.findByNome(nome, safeSize, offset).stream()
                .map(usuarioMapper::toResponse)
                .toList();
    }
}
