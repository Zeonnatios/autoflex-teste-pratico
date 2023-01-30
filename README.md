# Autoflex - Teste Prático

Projeto designado ao teste prático da empresa Projetada.

## Descrição do projeto

Neste projeto, fui responsável por criar uma aplicação full-stack utilizando o conceito de API, com o backend feito em Java com Spring Boot, o banco de dados PostgreSQL rodando num container docker, e o frontend feito com React.

## 🚀 Tecnologias

 As seguintes ferramentas foram usadas na construção do projeto:
 
-  [React](https://pt-br.reactjs.org/)
-  [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
-  [Spring](https://spring.io/)
-  [docker](https://docs.docker.com/)

## 🛠️ Features

- [x] Criar matérias primas
- [x] Listar matérias primas
- [x] Editar matérias primas
- [x] Excluir matérias primas
- [x] Criar produtos
- [x] Listar produtos
- [x] Editar produtos
- [x] Excluir produtos
- [x] Associar produtos a matérias primas
- [] Associar quantidade de matéria prima necessária para criar um produto.
- [] Consultar produtos a ser produzidos com base no estoque de matéria prima disponível.

## 📋 Pré-requisitos

Para rodar o projeto localmente, é necessário ter instalado na sua máquina:

-  [Node.js](https://nodejs.org/en/)
-  [Docker](https://docs.docker.com/get-docker/)
-  [Maven](https://maven.apache.org/download.cgi)

## 📓 Instalação

1. Realize o clone do repositório no diretório de sua preferência

```
  git clone https://github.com/Zeonnatios/recipes-app.git
```

2. Acesso o diretório do ambiente backend e utilize o comando mvn install para instalar as dependências necessárias:
```
  cd backend/factory-management
  mvn install
```

3. No mesmo diretório, irá criar e inicializar o container:
```
  docker-compose up
```
Criar e inicializar o container em stand-by:
```
docker-compose uo -d
```

4. Rode o projeto spring-boot:
```
  mvn spring-boot:run
```

5. De volta ao diretório raiz do projeto, entre no ambiente frontend e utilize o comando yarn install para instalar as dependências do proejto reac:

```
cd frontend
yarn install
```

6. Utilize o comando yarn start para iniciar a aplicação frontend:

```
yarn start
```

7. O frontend irá iniciar por padrão na porta 5173, por conta de ser desenvolvido com o Vite.
```
  localhost:5173
```

## 🚩 Próximos passos

* Implementar a associação da quantidade de matéria prima necessária para criar um produto.
* Implementar a consulta de produtos a ser produzidos com base no estoque de matéria prima disponível.
* Implementar o redux no frontend, para lidar com requisições e persistência de dados.
* Implementar uma documentação com swagger no backend.
* Implementar testes unitários no backend com JUnit.
* Implementar testes unitários no frontend com Jest e a biblioteca RTL, ou Cypress.

