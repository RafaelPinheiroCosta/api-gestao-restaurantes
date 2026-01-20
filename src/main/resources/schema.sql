CREATE TABLE usuarios (
                          id VARCHAR(36) PRIMARY KEY,
                          nome VARCHAR(120) NOT NULL,
                          email VARCHAR(160) NOT NULL,
                          login VARCHAR(60) NOT NULL,
                          senha_hash VARCHAR(120) NOT NULL,
                          status_cadastro VARCHAR(30) NOT NULL,
                          perfil_tipo VARCHAR(30) NOT NULL,
                          criado_em TIMESTAMP NOT NULL,
                          atualizado_em TIMESTAMP NOT NULL,
                          CONSTRAINT uk_usuario_email UNIQUE (email),
                          CONSTRAINT uk_usuario_login UNIQUE (login)
);

CREATE TABLE enderecos (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           usuario_id VARCHAR(36) NOT NULL,
                           rua VARCHAR(120) NOT NULL,
                           numero VARCHAR(20) NOT NULL,
                           complemento VARCHAR(80),
                           cidade VARCHAR(80) NOT NULL,
                           bairro VARCHAR(80) NOT NULL,
                           estado CHAR(2) NOT NULL,
                           cep VARCHAR(9) NOT NULL,

                           CONSTRAINT uk_endereco_usuario UNIQUE (usuario_id),
                           CONSTRAINT fk_endereco_usuario
                               FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                                   ON DELETE CASCADE
);
