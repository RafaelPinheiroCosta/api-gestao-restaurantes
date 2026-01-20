package com.rafaelcosta.api_gestao_restaurantes.application.mapper;

import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Endereco;
import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Usuario;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.*;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getLogin(),
                u.getStatusCadastro(),
                u.getPerfilTipo(),
                u.getEndereco() != null ? toEnderecoResponse(u.getEndereco()) : null,
                u.getCriadoEm(),
                u.getAtualizadoEm()
        );
    }

    public Endereco toEndereco(EnderecoRequest e) {
        if (e == null) return null;

        return Endereco.builder()
                .rua(e.rua())
                .numero(e.numero())
                .complemento(e.complemento())
                .bairro(e.bairro())
                .cidade(e.cidade())
                .estado(e.estado())
                .cep(e.cep())
                .build();
    }

    public EnderecoResponse toEnderecoResponse(Endereco e) {
        return new EnderecoResponse(
                e.getId(),
                e.getRua(),
                e.getNumero(),
                e.getComplemento(),
                e.getBairro(),
                e.getCidade(),
                e.getEstado(),
                e.getCep()
        );
    }
}
