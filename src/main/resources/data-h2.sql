-- Endereço 1
INSERT INTO enderecos (rua, numero, complemento, cidade, bairro, estado, cep)
VALUES ('Rua A', '100', 'Apto 10', 'Sao Paulo', 'Se','SP', '01000-000');

-- Usuario DONO_RESTAURANTE (necessário para endpoints protegidos)
INSERT INTO usuarios (id, nome, email, login, senha_hash, status_cadastro, perfil_tipo, endereco_id, criado_em, atualizado_em)
VALUES (
           '22222222-2222-2222-2222-222222222222',
           'Dono Local',
           'dono@local.com',
           'dono',
           '$2b$10$yGVJQFo1IyZxiT0MnvmIGOx8F0jazXpzssFgxDyqu..a2as0K2jei', -- Senha@123
           'ATIVO',
           'DONO_RESTAURANTE',
           1,
           CURRENT_TIMESTAMP,
           CURRENT_TIMESTAMP
       );
