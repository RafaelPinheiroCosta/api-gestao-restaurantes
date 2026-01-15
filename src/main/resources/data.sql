-- Endereço 1
INSERT INTO enderecos (rua, numero, complemento, cidade, estado, cep)
VALUES ('Rua A', '100', 'Apto 12', 'São Paulo', 'SP', '01010-000');

-- Usuário 1 (com endereço)
INSERT INTO usuarios (id, nome, email, login, senha_hash, status_cadastro, endereco_id, criado_em, atualizado_em)
VALUES (
    '11111111-1111-1111-1111-111111111111',
    'João da Silva',
    'joao@email.com',
    'joao',
    'HASH_Senha@123',
    'PENDENTE',
    1,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- Usuário 2 (sem endereço) - para testar LEFT JOIN funcionando
INSERT INTO usuarios (id, nome, email, login, senha_hash, status_cadastro, endereco_id, criado_em, atualizado_em)
VALUES (
    '22222222-2222-2222-2222-222222222222',
    'Maria Souza',
    'maria@email.com',
    'maria',
    'HASH_Senha@123',
    'PENDENTE',
    NULL,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);
