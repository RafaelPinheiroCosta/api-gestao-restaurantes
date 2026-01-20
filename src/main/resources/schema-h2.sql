DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS enderecos;

CREATE TABLE enderecos (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           rua VARCHAR(120) NOT NULL,
                           numero VARCHAR(20) NOT NULL,
                           complemento VARCHAR(80),
                           cidade VARCHAR(80) NOT NULL,
                           bairro VARCHAR(80) NOT NULL,
                           estado CHAR(2) NOT NULL,
                           cep VARCHAR(9) NOT NULL
);

CREATE TABLE usuarios (
                          id VARCHAR(36) PRIMARY KEY,
                          nome VARCHAR(120) NOT NULL,
                          email VARCHAR(160) NOT NULL,
                          login VARCHAR(60) NOT NULL,
                          senha_hash VARCHAR(120) NOT NULL,
                          status_cadastro VARCHAR(30) NOT NULL,
                          perfil_tipo VARCHAR(30) NOT NULL,
                          endereco_id BIGINT,
                          criado_em TIMESTAMP NOT NULL,
                          atualizado_em TIMESTAMP NOT NULL,

                          CONSTRAINT uk_usuario_email UNIQUE (email),
                          CONSTRAINT uk_usuario_login UNIQUE (login),
                          CONSTRAINT fk_usuario_endereco FOREIGN KEY (endereco_id) REFERENCES enderecos(id)
);
