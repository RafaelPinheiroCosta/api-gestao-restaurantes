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

A diferenciação entre Cliente e Dono de Restaurante é realizada por meio de uma **estratégia de composição**, utilizando um campo discriminador (`tipoUsuario`). O modelo está preparado para evolução futura com **tabelas de extensão 1:1**, caso sejam necessários atributos específicos para cada perfil.

---

## Tecnologias Utilizadas

- Java 21
- Maven
- Spring Boot 4.x
- Spring JDBC (`JdbcTemplate`)
- Spring Validation
- Spring Security (preparado para uso futuro)
- H2 Database (em memória para testes e desenvolvimento)
- Docker e Docker Compose
- MySQL (ambiente containerizado)
- Swagger/OpenAPI
- Lombok

---

## Banco de Dados

Durante o desenvolvimento e testes locais, a aplicação utiliza:

- **H2 em memória**, com inicialização automática via:
  - `schema.sql`
  - `data.sql`

A aplicação também está preparada para execução com **banco de dados relacional em ambiente containerizado**, utilizando **MySQL**, conforme configuração disponível no repositório.

---

## Execução da Aplicação com Docker (MySQL + API)

A aplicação pode ser executada em um ambiente containerizado utilizando **Docker** e **Docker Compose**, simulando um cenário próximo ao de produção.

### Pré-requisitos

- Docker
- Docker Compose (ou Docker Desktop)

Verifique a instalação com:

```bash
docker --version
docker compose version
```

## Arquivos envolvidos

- `Dockerfile` – construção da imagem da aplicação Spring Boot  
- `docker-compose.yml` – orquestração dos containers da API e do MySQL  
- `.env` – definição das variáveis de ambiente  

---

## Configuração das variáveis de ambiente

Exemplo de arquivo `.env`:

```ini
MYSQL_DATABASE=restaurantes_db
MYSQL_USER=restaurante_user
MYSQL_PASSWORD=restaurante_pass
MYSQL_ROOT_PASSWORD=root_pass

SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/restaurantes_db
SPRING_DATASOURCE_USERNAME=restaurante_user
SPRING_DATASOURCE_PASSWORD=restaurante_pass
```
Essas variáveis são utilizadas tanto pelo banco quanto pela aplicação.

---
## Subindo a aplicação

Na raiz do projeto, execute:

```bash
docker compose up --build
```
Esse comando irá:
- Construir a imagem da aplicação
- Criar o container do MySQL
- Subir os serviços de forma integrada, respeitando a ordem de inicialização definida no docker-compose.yml
- Executar automaticamente os scripts schema.sql e data.sql no container do MySQL, após o banco estar disponível

Os scripts de criação e carga inicial de dados são executados exclusivamente pelo container do banco de dados, por meio do mecanismo padrão de inicialização do MySQL. Dessa forma, a aplicação Spring Boot não é responsável por executar scripts de banco, evitando tentativas de acesso antes do banco estar completamente pronto para uso.

Essa abordagem garante maior previsibilidade na inicialização do ambiente, reduz acoplamento entre aplicação e banco de dados e evita problemas de sincronização durante o startup dos containers.

---
## Acessando a aplicação
Após a inicialização, a API estará disponível em:
- **API REST:**
```bash
http://localhost:8080
```
- **Swagger/OpenAPI:**
```bash
http://localhost:8080/swagger-ui/index.html
```
---
## Encerrando a execução

Para parar os containers:
```bash
docker compose down
```
Para remover também os volumes (apagando os dados):
```bash
docker compose down -v
```
---
## Execução e Testes da API
Os endpoints podem ser testados utilizando:
- Postman
- Insomnia

O repositório disponibiliza uma coleção Postman em formato JSON, contendo testes para todos os principais cenários da API:

A coleção pode ser encontrada em:
 [src/postman/postman_collection_api_restaurantes.json](src/postman/postman_collection_api_restaurantes.json)

A API segue o padrão REST e utiliza JSON como formato de comunicação.

---

## Evolução do Projeto
Com a conclusão da Fase 1, o projeto estabelece uma base arquitetural sólida, pronta para evolução.

As próximas fases incluirão a expansão do domínio de gestão de restaurantes, com novas entidades, regras de negócio mais complexas e refinamento técnico contínuo.

---

## Contexto Acadêmico

Este projeto faz parte da avaliação prática da PósTech FIAP.

O relatório técnico em PDF é o entregável oficial da disciplina.
Este repositório contém o código-fonte, os artefatos técnicos e os recursos utilizados como base para a construção desse relatório e para a continuidade do projeto nas próximas fases.