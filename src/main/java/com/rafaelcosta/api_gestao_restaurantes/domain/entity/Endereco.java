package com.rafaelcosta.api_gestao_restaurantes.domain.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private Long id;
    private String rua;
    private String numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;
}
