# Tourism API

## Visão Geral

Este projeto é uma API de turismo desenvolvida em Spring Boot, que fornece funcionalidades para gerenciar pontos
turísticos, reservas, autenticação de usuários, e avaliações de pontos turísticos. Ele utiliza JWT para autenticação de
usuários e suporta operações básicas de CRUD (criação, leitura, atualização e exclusão) para os principais recursos da
aplicação.

A API está documentada utilizando Swagger, o que facilita a navegação pelos endpoints disponíveis e testes via interface
visual.

## Funcionalidades

- Gerenciamento de Pontos Turísticos: Consulta e listagem de locais turísticos com suas descrições e avaliações médias.
- Gerenciamento de Avaliações: Usuários autenticados podem avaliar e comentar os pontos turísticos.
- Sistema de Reservas: Usuários podem realizar, alterar ou cancelar reservas em pontos turísticos.
- de Usuários: Registro e login de usuários com geração de token JWT para proteger endpoints.
- Documentação de API: A API está documentada com Swagger para fácil visualização e teste dos endpoints.

## Tecnologias Utilizadas

- Spring Boot: Framework principal para o desenvolvimento da API.
- Maven: Gerenciamento de dependências e build da aplicação.
- JWT (JSON Web Token): Autenticação e autorização de usuários.
- Hibernate: Implementação de JPA para persistência de dados.
- H2 Database: Banco de dados em memória para testes locais.
- Docker: Contêinerização da aplicação para fácil deploy.
- Swagger: Documentação e testes de API.

## Pré-requisitos

Antes de iniciar o projeto, certifique-se de ter o seguinte instalado em seu ambiente de desenvolvimento:

- Java 17 ou superior
- Maven 3.x
- Docker (opcional, se você deseja executar o projeto com Docker)

## Configuração e Execução do Projeto

1. Clonar o Repositório
    - Primeiro, clone o repositório para sua máquina local:

```
git clone https://github.com/seu-usuario/tourism-api.git
cd tourism-api
```

2. Compilar e Executar a Aplicação

#### Caso queira executar o projeto sem Docker, siga os passos abaixo:

1. Compile o projeto utilizando o Maven:

```
./mvnw clean install
```

2. Execute a aplicação:

```
./mvnw spring-boot:run
```

3. Acesse a API no seu navegador:

```
http://localhost:8080
```

4. Para acessar a documentação Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

### Executando com Docker

Se preferir utilizar Docker para facilitar o deploy, você pode seguir estes passos:

1. Construir a imagem Docker:

```
docker build -t tourism-api .
```

2. Executar a imagem Docker:

```
docker run -d -p 8080:8080 tourism-api
```

3. Verifique se a aplicação está rodando corretamente:

```
http://localhost:8080
```

# Testando a API

A API está documentada e acessível via Swagger. Após iniciar a aplicação, você pode abrir o Swagger UI e interagir com
os endpoints:

```
http://localhost:8080/swagger-ui/index.html
```

Você pode testar funcionalidades como:

- Autenticação de Usuário (/user/auth)
- Criação de Pontos Turísticos (/tourist-spot)
- Gestão de Avaliações (/assessment)
- Criação e Cancelamento de Reservas (/reservation)

# Banco de Dados H2

A aplicação utiliza um banco de dados em memória H2 para desenvolvimento e testes. Você pode acessar a interface web do
H2 para visualizar os dados persistidos:

```
http://localhost:8080/h2-console
```

### Credenciais de acesso ao H2:

- JDBC URL: jdbc:h2:mem:testdb
- Username: sa
- Password: (deixe em branco)

# Estrutura do Projeto

O projeto segue uma arquitetura baseada em camadas, organizada da seguinte forma:

- Controller: Responsável por gerenciar as requisições HTTP.
- Service: Contém a lógica de negócios.
- Repository: Responsável pela interação com o banco de dados.
- Model: Classes que representam as entidades do banco de dados.
- DTO: Classes para transferência de dados entre cliente e servidor.
- Security: Configurações de segurança e gerenciamento de tokens JWT.

# Testes

O projeto inclui testes unitários e de integração, que podem ser executados utilizando o Maven:

```
./mvnw test
```

# Deploy no Azure

### Configuração de CI/CD

O projeto está configurado para deploy contínuo (CD) utilizando GitHub Actions. O arquivo de workflow do GitHub Actions
faz o seguinte:

1. Build da Imagem Docker: O código é verificado e a imagem Docker é construída e enviada para o Docker Hub.
2. Deploy para Azure: A imagem Docker é implantada em um Azure Web App.

### Deploy Manual

Caso queira realizar o deploy manualmente, siga estas instruções:

1. Login no Azure: Acesse seu portal do Azure.
2. Criar um Azure Web App: Configure um Web App para contêineres, selecionando a opção de usar uma imagem Docker.
3. Definir as Configurações do Contêiner: Utilize a imagem gerada no Docker Hub para o seu Azure Web App.
