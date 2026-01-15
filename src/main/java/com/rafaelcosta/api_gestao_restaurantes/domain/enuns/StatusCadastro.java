package com.rafaelcosta.api_gestao_restaurantes.domain.enuns;

public enum StatusCadastro {

    PENDENTE,   // cadastro criado, mas ainda incompleto ou não validado
    ATIVO,      // usuário liberado para uso normal do sistema
    SUSPENSO,   // bloqueio temporário (reversível)
    BLOQUEADO   // bloqueio administrativo mais severo
}
