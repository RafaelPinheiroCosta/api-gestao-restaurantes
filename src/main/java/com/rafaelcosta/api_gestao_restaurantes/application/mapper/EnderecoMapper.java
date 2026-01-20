package com.rafaelcosta.api_gestao_restaurantes.application.mapper;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.EnderecoRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.EnderecoResponse;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.EnderecoUpdateRequest;
import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Endereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

    public EnderecoResponse toResponse(Endereco e) {
        if (e == null) return null;

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

    public Endereco fromCreateRequest(EnderecoRequest r) {
        if (r == null) return null;

        return Endereco.builder()
                .rua(r.rua())
                .numero(r.numero())
                .complemento(r.complemento())
                .bairro(r.bairro())
                .cidade(r.cidade())
                .estado(r.estado())
                .cep(r.cep())
                .build();
    }

    public Endereco fromUpdateRequest(EnderecoUpdateRequest r) {
        if (r == null) return null;

        return Endereco.builder()
                .rua(r.rua())
                .numero(r.numero())
                .complemento(r.complemento())
                .bairro(r.bairro())
                .cidade(r.cidade())
                .estado(r.estado())
                .cep(r.cep())
                .build();
    }
}
