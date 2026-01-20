package com.rafaelcosta.api_gestao_restaurantes.application.service;

import com.rafaelcosta.api_gestao_restaurantes.domain.exception.EmailJaCadastradoException;
import com.rafaelcosta.api_gestao_restaurantes.domain.exception.LoginJaCadastradoException;
import com.rafaelcosta.api_gestao_restaurantes.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioUniquenessValidator {

    private final UsuarioRepository usuarioRepository;

    public void validateForCreate(String email, String login) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailJaCadastradoException(email);
        }
        if (usuarioRepository.existsByLogin(login)) {
            throw new LoginJaCadastradoException(login);
        }
    }

    public void validateForUpdate(UUID id, String email, String login) {
        if (email != null && usuarioRepository.existsByEmailAndIdNot(email, id)) {
            throw new EmailJaCadastradoException(email);
        }
        if (login != null && usuarioRepository.existsByLoginAndIdNot(login, id)) {
            throw new LoginJaCadastradoException(login);
        }
    }
}
