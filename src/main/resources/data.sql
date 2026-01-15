-- Endereço 1
INSERT INTO enderecos (rua, numero, complemento, cidade, estado, cep)
VALUES ('Rua A', '100', 'Apto 12', 'São Paulo', 'SP', '01010-000');

-- Usuário 1 (com endereço)
INSERT INTO usuarios (
    id, nome, email, login, senha_hash, status_cadastro,
    perfil_tipo, endereco_id, criado_em, atualizado_em
)
VALUES (
    '11111111-1111-1111-1111-111111111111',
    'João da Silva',
    'joao@email.com',
    'joao',
    '$2b$10$yGVJQFo1IyZxiT0MnvmIGOx8F0jazXpzssFgxDyqu..a2as0K2jei', -- Senha@123
    'PENDENTE',
    'CLIENTE',
    1,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);


-- Usuário 2 (sem endereço) - para testar LEFT JOIN funcionando
INSERT INTO usuarios (
    id, nome, email, login, senha_hash, status_cadastro,
    perfil_tipo, endereco_id, criado_em, atualizado_em
)
VALUES (
    '22222222-2222-2222-2222-222222222222',
    'Maria Souza',
    'maria@email.com',
    'maria',
    '$2b$10$yGVJQFo1IyZxiT0MnvmIGOx8F0jazXpzssFgxDyqu..a2as0K2jei', -- Senha@123
    'PENDENTE',
    'DONO_RESTAURANTE',
    NULL,
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

