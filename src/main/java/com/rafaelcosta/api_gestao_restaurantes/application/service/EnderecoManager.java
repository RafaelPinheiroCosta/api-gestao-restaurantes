package com.rafaelcosta.api_gestao_restaurantes.application.service;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.EnderecoRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.dto.EnderecoUpdateRequest;
import com.rafaelcosta.api_gestao_restaurantes.application.mapper.EnderecoMapper;
import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Endereco;
import com.rafaelcosta.api_gestao_restaurantes.domain.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoManager {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    @Transactional
    public Endereco upsertEndereco(UUID usuarioId, Endereco enderecoAtual, EnderecoRequest request) {
        if (request == null) return enderecoAtual;

        Endereco novo = enderecoMapper.fromCreateRequest(request);

        if (enderecoAtual == null) {
            Long id = enderecoRepository.save(usuarioId, novo);
            novo.setId(id);
            return novo;
        }

        enderecoRepository.update(novo, enderecoAtual.getId());
        novo.setId(enderecoAtual.getId());
        return novo;
    }

    @Transactional
    public Endereco upsertEndereco(UUID usuarioId, Endereco enderecoAtual, EnderecoUpdateRequest request) {
        if (request == null) return enderecoAtual;

        if (enderecoAtual == null) {
            Endereco novo = enderecoMapper.fromUpdateRequest(request);
            Long id = enderecoRepository.save(usuarioId, novo);
            novo.setId(id);
            return novo;
        }

        Endereco patch = enderecoMapper.fromUpdateRequest(request);

        Endereco merged = Endereco.builder()
                .rua(merge(patch.getRua(), enderecoAtual.getRua()))
                .numero(merge(patch.getNumero(), enderecoAtual.getNumero()))
                .complemento(merge(patch.getComplemento(), enderecoAtual.getComplemento()))
                .bairro(merge(patch.getBairro(), enderecoAtual.getBairro()))
                .cidade(merge(patch.getCidade(), enderecoAtual.getCidade()))
                .estado(merge(patch.getEstado(), enderecoAtual.getEstado()))
                .cep(merge(patch.getCep(), enderecoAtual.getCep()))
                .build();

        enderecoRepository.update(merged, enderecoAtual.getId());
        merged.setId(enderecoAtual.getId());
        return merged;
    }

    private String merge(String novoValor, String valorAtual) {
        return novoValor != null ? novoValor : valorAtual;
    }
}
