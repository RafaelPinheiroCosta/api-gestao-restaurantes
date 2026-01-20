package com.rafaelcosta.api_gestao_restaurantes.application.service;

import com.rafaelcosta.api_gestao_restaurantes.application.dto.UsuarioChangePasswordRequest;
import com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.Usuario;
import com.rafaelcosta.api_gestao_restaurantes.domain.exception.SenhaAtualInvalidaException;
import com.rafaelcosta.api_gestao_restaurantes.domain.exception.UsuarioNaoEncontradoException;
import com.rafaelcosta.api_gestao_restaurantes.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioPasswordService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void changePassword(UUID id, UsuarioChangePasswordRequest request) {
        Usuario atual = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id.toString()));

        if (!passwordEncoder.matches(request.senhaAtual(), atual.getSenhaHash())) {
            throw new SenhaAtualInvalidaException();
        }

        String novoHash = passwordEncoder.encode(request.novaSenha());
        usuarioRepository.updateSenhaHash(id, novoHash);
    }
}
