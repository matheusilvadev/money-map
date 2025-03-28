# MoneyMap

MoneyMap é uma aplicação web para controle de fluxo de caixa. Permite adicionar atividades com descrição, data, valor e tipo (entrada ou saída).

## Funcionalidades

* Adicionar novas atividades financeiras.
* Visualizar histórico de atividades.
* Cálculo automático do saldo atual.

## Tecnologias Utilizadas

### Frontend

* Next.js
* TypeScript
* Lucide React
* Shadcn UI
* Axios

### Backend

* Java
* Spring Framework
* Spring Data JPA
* Spring Security
* Spring Web
* JWT (JSON Web Tokens)
* Clean Architecture

## Pré-requisitos

Antes de começar, certifique-se de ter instalado:

* Node.js e npm (ou yarn)
* Java Development Kit (JDK)
* Banco de dados PostgreSQL (ou outro de sua preferência)

## Configuração

### Backend

1.  Clone o repositório do backend.
2.  Navegue até o diretório do projeto.
3.  Configure as propriedades do banco de dados no arquivo `application.properties`.
4.  Execute a aplicação Spring Boot.

### Frontend

1.  Clone o repositório do frontend.
2.  Navegue até o diretório do projeto.
3.  Instale as dependências com `npm install` (ou `yarn install`).
4.  Execute a aplicação com `npm run dev` (ou `yarn dev`).

## Endpoints da API

* `POST /activities`: Adiciona uma nova atividade.
* `GET /activities`: Lista todas as atividades.
* `GET /activities/{id}`: Busca uma atividade por ID.
* `DELETE /activities/{id}`: Remove uma atividade.

## Considerações sobre o Backend

* Foi utilizada Clean Architecture para melhor organização e manutenibilidade do código.
* A autenticação é feita via JWT, garantindo a segurança da API.
* O Spring Security foi configurado para proteger os endpoints da API.

## Contribuição

Contribuições são sempre bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.
