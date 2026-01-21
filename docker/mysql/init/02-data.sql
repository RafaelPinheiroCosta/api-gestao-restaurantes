-- Usuario 1 (com endereço)
INSERT INTO usuarios (
    id, nome, email, login, senha_hash, status_cadastro,
    perfil_tipo, criado_em, atualizado_em
)
VALUES (
           '22222222-2222-2222-2222-222222222222',
           'João da Silva',
           'joao@email.com',
           'joao',
           '$2b$10$yGVJQFo1IyZxiT0MnvmIGOx8F0jazXpzssFgxDyqu..a2as0K2jei', -- Senha@123
           'PENDENTE',
           'CLIENTE',
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP
       );

-- Usuario 2 (sem endereço) - para testar LEFT JOIN funcionando
INSERT INTO usuarios (
    id, nome, email, login, senha_hash, status_cadastro,
    perfil_tipo, criado_em, atualizado_em
)
VALUES (
           '11111111-1111-1111-1111-111111111111',
           'Dono Restaurante',
           'dono@email.com',
           'dono',
           '$2b$10$yGVJQFo1IyZxiT0MnvmIGOx8F0jazXpzssFgxDyqu..a2as0K2jei', -- Senha@123
           'PENDENTE',
           'DONO_RESTAURANTE',
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP
       );

-- Endereço do Usuario 1 (João)
INSERT INTO enderecos (
    usuario_id, rua, numero, complemento, cidade, bairro, estado, cep
)
VALUES (
           '22222222-2222-2222-2222-222222222222',
           'Rua A',
           '100',
           'Apto 12',
           'São Paulo',
           'Se',
           'SP',
           '01010-000'
       );
