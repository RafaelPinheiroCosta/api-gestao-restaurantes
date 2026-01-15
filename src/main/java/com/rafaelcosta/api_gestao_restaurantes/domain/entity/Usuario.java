package com.rafaelcosta.api_gestao_restaurantes.domain.entity;

import com.rafaelcosta.api_gestao_restaurantes.domain.enuns.StatusCadastro;
import com.rafaelcosta.api_gestao_restaurantes.domain.perfil.Perfil;
import com.rafaelcosta.api_gestao_restaurantes.domain.perfil.Perfis;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Usuario {
    private UUID id;
    private String nome;
    private String email;
    private String login;
    private String senhaHash;
    private StatusCadastro statusCadastro;

    private String perfilTipo;

    @Setter(AccessLevel.NONE)
    private Perfil perfil;

    private Endereco endereco;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    public void carregarPerfil() {
        this.perfil = Perfis.fromTipo(this.perfilTipo);
    }

    public Perfil perfil() {
        if (this.perfil == null) carregarPerfil();
        return this.perfil;
    }
}
