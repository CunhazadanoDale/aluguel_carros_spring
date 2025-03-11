# Sistema de Aluguel de Carros

Sistema de gerenciamento de aluguel de carros com **Spring Boot**, permitindo **CRUD** para clientes, veículos e contratos de aluguel. Controla a disponibilidade de veículos, registra datas de retirada e devolução e calcula o valor do aluguel com base nos dias de uso.

## Tecnologias

- **Spring Boot**
- **JPA (Java Persistence API)**
- **H2 Database** (para testes)
- **Spring Security** (opcional)
- **Swagger** (documentação)

## Estrutura do Projeto

- **model**: Entidades `Cliente`, `Veiculo`, `ContratoAluguel`.
- **repository**: Repositórios para acesso aos dados.
- **controller**: Controladores REST.
- **service**: Lógica de negócios.
- **exception**: Exceções personalizadas.
