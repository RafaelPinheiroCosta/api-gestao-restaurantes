# API de Gestão de Restaurantes – Fase 1

Projeto desenvolvido como parte do **Tech Challenge – Fase 1** da PósTech FIAP, na disciplina de **Arquitetura e Desenvolvimento Java**.

Esta API representa a primeira etapa de um sistema backend compartilhado para gestão de restaurantes, desenvolvido de forma incremental conforme as fases do curso.

---

## Contexto do Projeto

Na região proposta pelo desafio, um grupo de restaurantes decidiu se unir para desenvolver um **sistema único e compartilhado de gestão**, reduzindo custos e permitindo evolução contínua da solução.

O sistema permitirá que:
- Restaurantes gerenciem seus dados e operações
- Clientes possam consultar informações e interagir com os estabelecimentos

Devido a limitações de recursos, o projeto é entregue **em fases**, sendo esta a **primeira**.

---

## Objetivo da Fase 1

Desenvolver um **backend robusto utilizando Spring Boot**, aplicando os conceitos fundamentais de arquitetura, boas práticas e persistência de dados aprendidos nesta fase do curso.

---

## Funcionalidades Implementadas até o Momento

### Gestão de Usuários

A aplicação implementa o **CRUD completo de usuários**, atendendo aos requisitos iniciais do desafio:

- Cadastro de usuário
- Listagem de usuários com paginação
- Busca de usuário por ID
- Atualização de dados cadastrais
- Exclusão de usuário
- Registro da data de criação e última atualização
- Criptografia de senha utilizando `PasswordEncoder`
- Garantia de unicidade de e-mail e login
- Controle do status do cadastro do usuário

### Endereço

- Cadastro de endereço associado ao usuário
- Atualização e persistência do endereço de forma independente

---

## Modelagem Atual

### Usuario
Campos principais:
- `id` (UUID)
- `nome`
- `email` (único)
- `login` (único)
- `senhaHash`
- `statusCadastro`
- `criadoEm`
- `atualizadoEm`
- `endereco`

### Endereco
Campos principais:
- `id`
- `rua`
- `numero`
- `complemento`
- `cidade`
- `estado`
- `cep`

---

## Tecnologias Utilizadas

- Java 25
- Spring Boot 4.x
- Spring JDBC (JdbcTemplate)
- Banco de dados H2 (ambiente de testes)
- Lombok
- Maven

---

## Banco de Dados

Nesta fase, a aplicação utiliza:
- **H2 em memória**, com inicialização automática via:
    - `schema.sql`
    - `data.sql`

Essa abordagem facilita os testes locais e a validação dos endpoints via Postman.

---

## Testes de API

Os endpoints podem ser testados utilizando ferramentas como:
- Postman
- Insomnia

A API segue o padrão REST e utiliza JSON como formato de comunicação.

---

## Planejamento das Próximas Etapas

Conforme o enunciado do Tech Challenge, as próximas fases do projeto irão contemplar:

- Separação de endpoints para troca de senha
- Validação de login e senha
- Busca de usuários por nome
- Tipos de usuário (Cliente e Dono de Restaurante)
- Documentação com Swagger/OpenAPI
- Padronização de erros com Problem Detail (RFC 7807)
- Persistência em banco relacional via Docker (MySQL ou PostgreSQL)
- Dockerização da aplicação
- Autenticação (opcional) com Spring Security e JWT

---

## Contexto Acadêmico

Este projeto faz parte da avaliação prática da **PósTech FIAP**, sendo o **relatório técnico em PDF** o entregável oficial da disciplina.

O repositório contém o código-fonte utilizado como base para a construção desse relatório.

---
