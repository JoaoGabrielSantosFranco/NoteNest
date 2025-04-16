# NoteNest - Aplicação Segura de Bloco de Notas

O NoteNest é uma aplicação segura de bloco de notas onde os usuários podem criar, editar, excluir e visualizar suas anotações pessoais. A aplicação oferece autenticação de usuários via tokens JWT e armazena as notas de forma segura em um banco de dados MySQL, executado em um container Docker.

## Funcionalidades

- **Autenticação de Usuários:**
  - Os usuários podem se registrar e fazer login no sistema.
  - Após o login bem-sucedido, os usuários recebem um token JWT para autenticação segura nas requisições subsequentes.

- **Gerenciamento de Notas:**
  - Criar, editar e excluir notas.
  - Visualizar todas as notas associadas ao usuário autenticado.

- **Armazenamento Seguro:**
  - As notas e informações dos usuários são armazenadas de forma segura em um banco de dados MySQL.

## Tecnologias Utilizadas

- **Backend:** Spring Boot (Java)
- **Banco de Dados:** MySQL
- **Autenticação:** JWT (JSON Web Tokens)
- **Containerização:** Docker

## Documentação da API com Swagger

O **Swagger** foi integrado à aplicação para fornecer uma documentação interativa e intuitiva da API. Com o Swagger, você pode facilmente explorar todos os endpoints da API, testar as requisições e visualizar as respostas diretamente da interface do navegador.

### Acessando o Swagger

Após rodar a aplicação, você pode acessar a documentação da API através da interface do **Swagger UI** em:

```
http://localhost:8080/swagger-ui.html
```

Na interface, você encontrará uma lista completa de todos os endpoints disponíveis, com descrições detalhadas, parâmetros de entrada e tipos de resposta. Você também pode testar os endpoints diretamente da interface, enviando requisições e visualizando as respostas sem a necessidade de ferramentas externas.

### Exemplos de Funcionalidades no Swagger:

- **Visualização de Endpoints**: Todos os endpoints da API estarão documentados, incluindo os métodos HTTP (GET, POST, PUT, DELETE), parâmetros e respostas esperadas.
- **Testar Requisições**: Você pode enviar requisições de teste diretamente pela interface do Swagger para ver como a API responde.

## Como Começar

### Clonar o repositório

```bash
git clone https://github.com/usuario-ao-seu-repositorio/notenest.git
cd notenest
```

### Configuração do Docker

1. Construa e inicie os containers Docker para o banco de dados MySQL:

```bash
docker-compose up --build
```

2. Após os containers estarem rodando, você pode acessar a aplicação em [http://localhost:8080](http://localhost:8080).

### Rodando a Aplicação Localmente (sem Docker)

1. Clone o repositório e entre no diretório do projeto:

```bash
git clone https://github.com/usuario-ao-seu-repositorio/notenest.git
cd notenest
```

2. Abra o projeto na sua IDE preferida e rode a aplicação Spring Boot.

3. Certifique-se de que o banco de dados MySQL está configurado e rodando localmente, e atualize o arquivo `application.properties` com os detalhes de conexão com o banco de dados.
