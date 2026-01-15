package com.rafaelcosta.api_gestao_restaurantes.domain.perfil;

public interface Perfil {
    String role(); // ex: "ROLE_CLIENTE" / "ROLE_DONO_RESTAURANTE"
    String tipo(); // ex: "CLIENTE" / "DONO_RESTAURANTE"
}
