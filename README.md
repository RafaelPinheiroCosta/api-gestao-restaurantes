# API de Gestão de Restaurantes – Fase 1

Projeto desenvolvido como parte do **Tech Challenge – Fase 1** da PósTech FIAP, na disciplina de **Arquitetura e Desenvolvimento Java**.

Esta API representa a **primeira etapa concluída** de um sistema backend compartilhado para gestão de restaurantes, desenvolvido de forma incremental conforme as fases do curso.

---

## Contexto do Projeto

Na região proposta pelo desafio, um grupo de restaurantes decidiu se unir para desenvolver um **sistema único e compartilhado de gestão**, reduzindo custos com soluções individuais e estabelecendo uma base tecnológica comum para evolução contínua do sistema.

O sistema tem como objetivo permitir que:

- Restaurantes gerenciem seus dados e operações
- Clientes possam consultar informações e interagir com os estabelecimentos

Devido à complexidade do domínio e à proposta pedagógica do desafio, o projeto é estruturado **em fases**, sendo esta a **primeira fase concluída e funcional**.

---

## Objetivo da Fase 1

Entregar uma **base backend completa e funcional**, utilizando **Spring Boot**, aplicando de forma integrada os conceitos fundamentais de arquitetura, boas práticas de desenvolvimento, organização de código, persistência de dados e padronização de interfaces.

---

## Funcionalidades Implementadas

### Gestão de Usuários

A aplicação implementa o **CRUD completo de usuários**, contemplando:

- Cadastro, listagem, busca, atualização e exclusão de usuários
- Paginação na listagem
- Registro automático de data de criação e atualização
- Criptografia de senha utilizando `PasswordEncoder`
- Garantia de unicidade de e-mail e login
- Controle de status do cadastro do usuário
- Endpoints específicos para validação de login e troca de senha
- Busca de usuários por nome
- Diferenciação de tipos de usuário (Cliente e Dono de Restaurante)

### Endereço

- Cadastro de endereço associado ao usuário
- Atualização e persistência do endereço de forma independente

---

## Documentação, Erros e Segurança

A API já contempla:

- Documentação completa com **Swagger/OpenAPI**
- Padronização de respostas de erro utilizando **Problem Detail (RFC 7807)**
- Tratamento global de exceções
- Estrutura preparada para autenticação com **Spring Security**, com JWT configurável conforme evolução do projeto

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
- `tipoUsuario`
- `criadoEm`
- `atualizadoEm`
- `endereco`

### Endereco

Campos principais:

- `id`
- `rua`
- `numero`
- `complemento`
- `bairro`
- `cidade`
- `estado`
- `cep`

---

## Tecnologias Utilizadas

- Java 21
- Maven
- Spring Boot 4.x
- Spring JDBC (`JdbcTemplate`)
- Spring Validation
- Spring Security (preparado para uso futuro)
- H2 Database (em memória para testes e desenvolvimento)
- Docker (para ambiente com MySQL em produção)
- Swagger/OpenAPI
- Lombok

---

## Banco de Dados

Durante o desenvolvimento e testes, a aplicação utiliza:

- **H2 em memória**, com estruturação e inicialização de alguns dados de forma automática via:
  - `schema.sql`
  - `data.sql`

A aplicação também está preparada para execução com **banco de dados relacional em ambiente containerizado**, utilizando **Docker** e **MySQL**, conforme configuração disponível no repositório.

---

## Execução e Testes de API

A aplicação pode ser executada localmente ou via Docker.

Os endpoints podem ser testados utilizando:

- Postman
- Insomnia

O repositório disponibiliza uma **coleção de testes em formato JSON para Postman** [link](src/postman/postamn_collection_api_restaurantes.json), permitindo validar todos os endpoints implementados.

A API segue o padrão **REST** e utiliza **JSON** como formato de comunicação.

---

## Evolução do Projeto

Com a conclusão desta Fase 1, o projeto estabelece uma **base arquitetural sólida**, pronta para evolução.

As próximas fases do desenvolvimento serão direcionadas à **expansão do domínio do sistema de gestão de restaurantes**, com inclusão de novas funcionalidades, refinamento das regras de negócio e aprofundamento técnico, sempre alinhados ao contexto apresentado no enunciado do desafio.

---

## Contexto Acadêmico

Este projeto faz parte da avaliação prática da **PósTech FIAP**, sendo o **relatório técnico em PDF** o entregável oficial da disciplina.

O repositório contém o **código-fonte** utilizado como base para a construção desse relatório e para a continuidade do projeto nas próximas fases.

---
