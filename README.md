# Investment Portfolio - API de Carteira de Investimentos

API em Java para consultar ativos de uma planilha do Google e retornar informações da carteira de investimentos.

## Exemplos de uso

GET /v1/carteira/ativo/{nome_ativo}
## Retorna

```json
{
  "preco": 120.55,
  "quantidade": 10,
  "totalInvestido": 1205.50
}
```
## Tecnologias

- Java 17

- Spring Boot

- Maven

- Jackson

## Instruções

1. Clone o projeto
2. Rode com:

mvn spring-boot:run