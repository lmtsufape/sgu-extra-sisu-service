# SGU Extra Sisu Service

[Swagger Dev (Documentação da API)](http://localhost:8085/swagger-ui/index.html)

Projeto Java responsável pelo gerenciamento de vagas remanescentes (Extra Sisu). Este microsserviço atua como uma camada de orquestração, integrando-se ao **SGU Editais Service** para gerenciar inscrições, status personalizados e históricos de etapas.

---

## Sobre o Projeto

Este projeto foi desenvolvido para atender às demandas de preenchimento de vagas não ocupadas pelo Sisu regular. Ele utiliza comunicação síncrona via OpenFeign para consumir recursos do sistema de editais, implementando padrões de resiliência (Circuit Breaker) para garantir estabilidade.

---

## Tecnologias Utilizadas

* [Java 25](https://openjdk.org/projects/jdk/25/) - Linguagem de programação
* [Spring Boot 4](https://spring.io/projects/spring-boot) - Framework principal
* [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign) - Cliente HTTP declarativo
* [Resilience4j](https://resilience4j.readme.io/) - Tolerância a falhas (Circuit Breaker)
* [Maven](https://maven.apache.org/) - Gerenciador de dependências e build
* [Docker](https://www.docker.com/) - Plataforma de containerização

---

## Como Começar

Siga estas instruções para colocar o projeto em funcionamento em sua máquina local para fins de desenvolvimento e teste.

### Pré-requisitos

Para que o **Extra Sisu** funcione corretamente, ele precisa se comunicar com os outros serviços do ecossistema, **especialmente o serviço de Editais**.

Clone os seguintes repositórios:

- [SGU Discovery Service](https://github.com/lmtsufape/sgu-discovery-service)
- [SGU Gateway Service](https://github.com/lmtsufape/sgu-gateway-service)
- [SGU Config Service](https://github.com/lmtsufape/sgu-config-service)
- [SGU Auth Service](https://github.com/lmtsufape/sgu-auth-server)
- [SGU Editais Service](https://github.com/lmtsufape/sgu-editais-service) 

Certifique-se de ter instalado em sua máquina:

* JDK 25
* Maven 3.9.9 ou superior
* Docker (para rodar os bancos de dados e serviços auxiliares)

Após clonar os repositórios, execute o seguinte comando (seguindo a ordem acima) em cada diretório para subir a infraestrutura:

```bash
    docker compose up -d --build