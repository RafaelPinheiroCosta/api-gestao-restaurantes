package com.rafaelcosta.api_gestao_restaurantes.domain.entity.usuario.perfil;

public final class Perfis {
    private Perfis() {}

    public static Perfil fromTipo(String tipo) {
        if (tipo == null) return new PerfilCliente();

        return switch (tipo) {
            case "DONO_RESTAURANTE" -> new PerfilDonoRestaurante();
            case "CLIENTE" -> new PerfilCliente();
            default -> new PerfilCliente();
        };
    }
}
