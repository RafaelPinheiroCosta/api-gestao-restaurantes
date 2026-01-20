package com.rafaelcosta.api_gestao_restaurantes.application.mapper;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioResponse;
import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final EnderecoMapper enderecoMapper;

    public UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getLogin(),
                u.getStatusCadastro(),
                u.getPerfilTipo(),
                enderecoMapper.toResponse(u.getEndereco()),
                u.getCriadoEm(),
                u.getAtualizadoEm()
        );
    }
}
