package com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.perfil;

public class PerfilCliente implements Perfil {

    @Override
    public String role() {
        return "ROLE_CLIENTE";
    }

    @Override
    public String tipo() {
        return "CLIENTE";
    }

}
