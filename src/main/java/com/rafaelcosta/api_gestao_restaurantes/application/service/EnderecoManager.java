package com.rafaelcosta.api_gestao_restaurantes.application.service;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.EnderecoRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.mapper.UsuarioMapper;
import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Endereco;
import com.rafaelcosta.api_gestao_restaurantes.domain.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnderecoManager {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioMapper usuarioMapper;

    @Transactional
    public Endereco upsertEndereco(Endereco enderecoAtual, EnderecoRequest request) {
        if (request == null) {
            return enderecoAtual;
        }

        Endereco novo = usuarioMapper.toEndereco(request);

        if (enderecoAtual == null) {
            Long id = enderecoRepository.save(novo);
            novo.setId(id);
            return novo;
        }

        enderecoRepository.update(novo, enderecoAtual.getId());
        novo.setId(enderecoAtual.getId());
        return novo;
    }
}
