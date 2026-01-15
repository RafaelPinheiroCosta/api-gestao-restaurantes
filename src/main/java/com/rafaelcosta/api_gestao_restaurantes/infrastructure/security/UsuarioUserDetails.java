package com.rafaelcosta.api_gestao_restaurantes.infrastructure.security;

import com.rafaelcosta.api_gestao_restaurantes.domain.entity.Usuario;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
public class UsuarioUserDetails implements UserDetails {

    private final Usuario usuario;

    public UsuarioUserDetails(Usuario usuario) {
        this.usuario = usuario;
    }

    public UUID getUsuarioId() {
        return usuario.getId();
    }

    public String getPerfilTipo() {
        return usuario.getPerfilTipo();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(usuario.perfil().role()));
    }

    @Override
    public String getPassword() {
        return usuario.getSenhaHash();
    }

    @Override
    public String getUsername() {
        return usuario.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // ponto de evolução: bloquear login se statusCadastro != ATIVO
        return true;
    }
}
