# Investment Portfolio - API de Carteira de Investimentos

API em Java para consultar ativos de uma planilha do Google e retornar informações da carteira de investimentos.

##  Funcionalidades

- ✅ Busca de um ativo por nome (ex: `MXRF11`, `visc11`, etc.)
- ✅ Integração com planilha do Google Sheets
- ✅ Cálculo do total investido por ativo

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- Google Sheets API
- Jackson (JSON)

Consulta um ativo específico da carteira.

##### Exemplo de requisição:
```http
GET /v1/carteira/ativo/MXRF11

```json
{
  "preco": 120.55,
  "quantidade": 10,
  "totalInvestido": 1205.50
}
```


## Instruções

1. Clone o projeto
     -##git clone https://github.com/seu-usuario/seu-repositorio.git
2. Adicione as credenciais da Google API:
      -Crie o arquivo credentials.json com suas credenciais do Google Sheets.
3.  Execute o projeto:
      mvn spring-boot:run
