# ✅ Todo Task API

API REST para gerenciamento de tarefas, com autenticação JWT, autorização baseada em roles e controle de usuários.

---

# 📌 Sobre o projeto

Esta API foi desenvolvida para simular um sistema real de gerenciamento de tarefas, permitindo que usuários criem, atualizem, concluam e organizem tarefas.

O projeto vai além de um CRUD simples, implementando conceitos importantes de backend moderno como:

- autenticação JWT
- autorização com Spring Security
- tratamento global de exceções
- validações
- arquitetura REST
- proteção de rotas
- separação por camadas

---

# 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring MVC
- JPA / Hibernate
- MySQL
- Flyway
- Maven

---

# 🔐 Autenticação

A API utiliza autenticação baseada em JWT.

## Fluxo

1. Usuário realiza login
2. Recebe um token JWT
3. Envia o token nas requisições protegidas

```http
Authorization: Bearer SEU_TOKEN
```

---

# 👮 Autorização

O sistema utiliza autorização baseada em roles.

Exemplo:

- ROLE_USER
- ROLE_ADMIN

As permissões são controladas através do Spring Security.

---

# 📚 Funcionalidades

## 👤 Usuário

- Cadastro de usuário
- Login
- Criptografia de senha
- Controle de permissões

---

## ✅ Tarefas

- Criar tarefas
- Atualizar tarefas
- Listar tarefas
- Excluir tarefas
- Marcar como concluída

---

## 🔐 Segurança

- Rotas protegidas
- Validação de token JWT
- Controle de acesso por perfil
- Tratamento de autenticação e autorização

---

# 📡 Principais endpoints

## 🔐 Autenticação

### Login

```http
POST /auth/login
```

### Cadastro

```http
POST /auth/register
```

---

## ✅ Tasks

### Criar task

```http
POST /tasks
```

### Listar tasks

```http
GET /tasks
```

### Atualizar task

```http
PUT /tasks/{id}
```

### Deletar task

```http
DELETE /tasks/{id}
```

---

# ⚙️ Como rodar o projeto

## Clone o repositório

```bash
git clone https://github.com/dionathanpassos/todotask-api.git
```

---

## Acesse a pasta

```bash
cd todotask-api
```

---

## Configure as variáveis de ambiente

```env
TODO_DB_URL=jdbc:mysql://localhost:3306/todotask_api
TODO_DB_USER=root
TODO_DB_PASSWORD=sua_senha
JWT_SECRET=sua_chave_secreta
```

---

## Execute o projeto

```bash
./mvnw spring-boot:run
```

---

# ⚙️ Configuração do banco

Crie um banco MySQL:

```sql
CREATE DATABASE todotask_api;
```

---

# 🧪 Exemplo de requisição

## Cadastro

```http
POST /auth/register
```

```json
{
  "name": "Dionathan",
  "email": "user@email.com",
  "password": "123456"
}
```

---

## Login

```http
POST /auth/login
```

```json
{
  "email": "user@email.com",
  "password": "123456"
}
```

---

## Resposta

```json
{
  "token": "jwt_token_aqui"
}
```

---

# ❗ Tratamento de erros

A API segue um padrão padronizado de resposta para erros.

## Exemplo

```json
{
  "status": 401,
  "error": "Unauthorized",
  "message": "Token inválido ou expirado",
  "path": "/tasks",
  "method": "GET",
  "timestamp": "2026-05-14T21:58:24"
}
```

---

# 🧠 Conceitos implementados

- Autenticação JWT
- Autorização com Spring Security
- Filtro JWT customizado
- Global Exception Handler
- DTOs
- Validações
- Separação Controller / Service / Repository
- Password Encoder
- Roles e Authorities
- Proteção de endpoints
- Configuração stateless

---


# 👨‍💻 Autor

Desenvolvido por Dionathan Passos

---

# 📬 Contato

Email: devdionathanpassos@gmail.com

---

# ⭐ Observação

Este projeto foi desenvolvido com foco em aprendizado e simulação de cenários reais de backend, aplicando boas práticas de arquitetura, segurança e desenvolvimento de APIs REST.
