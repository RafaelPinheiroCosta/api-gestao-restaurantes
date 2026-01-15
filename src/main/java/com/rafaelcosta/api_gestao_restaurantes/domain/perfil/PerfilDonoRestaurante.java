package com.rafaelcosta.api_gestao_restaurantes.domain.perfil;

public class PerfilDonoRestaurante implements Perfil {

    @Override
    public String role() {
        return "ROLE_DONO_RESTAURANTE";
    }

    @Override
    public String tipo() {
        return "DONO_RESTAURANTE";
    }

}
