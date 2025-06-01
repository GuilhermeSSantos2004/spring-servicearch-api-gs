# ServiceArch - Projeto de Consumo de API RESTful

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen)  
![Java](https://img.shields.io/badge/Java-17-blue)
![REST API](https://img.shields.io/badge/REST-API-blue)
![Status](https://img.shields.io/badge/Status-100%25-green)

## 👥 Integrantes

- Laura Claro Mathias – RM98747
- Guilherme Silva Dos Santos – RM551168
  
---

## ✅ Descrição do Projeto

Este projeto implementa uma API RESTful modular para gerenciar pessoas identificadas em imagens.  
O sistema consome a API pública [ViaCEP](https://viacep.com.br/) para buscar informações de endereço a partir do CEP informado.

---

## ✅ Requisitos Atendidos

| Critério                                                                                  | Status |
|-------------------------------------------------------------------------------------------|--------|
| Consumo de API RESTful externa (ViaCEP)                                                   | ✔️     |
| Organização modular baseada em serviços independentes e reutilizáveis                     | ✔️     |
| Separação clara entre camadas de controle, serviço e dados                                | ✔️     |
| Adoção de padrões REST e JSON (e suporte a outros se necessário)                          | ✔️     |

---

## ✅ Organização do Projeto

**Padrão de camadas (MVC):**
- `model`       → Entidades de dados (`Person`)
- `service`     → Lógica de negócio e consumo de API (`PersonService`, `ViaCepService`)
- `controller`  → Endpoints REST (`PersonController`)
- `repository`  → Acesso ao banco de dados (`PersonRepository`)




### Consultar todas as pessoas cadastradas

```http
GET /api/persons

Exemplo de resposta:

[
  {
    "id": 1,
    "externalId": 1,
    "imagePath": "caminho/da/imagem.jpg",
    "name": "Guilherme",
    "status": 1,
    "identified": 1,
    "ncep": "01223-239"
  },
  {
    "id": 2,
    "externalId": 2,
    "imagePath": "caminho/da/imagem2.jpg",
    "name": "Ana",
    "status": 3,
    "identified": 0,
    "ncep": "01111-000"
  }
]
```
![image](https://github.com/user-attachments/assets/2f801905-00d7-4a63-a298-7261722e0dd8)


## ✅ Consumo de API RESTful (ViaCEP)

Ao consultar o CEP de uma pessoa, o sistema faz uma requisição HTTP GET à API ViaCEP, busca os dados do endereço e retorna em formato JSON.

Exemplo de Resposta (JSON)

![image](https://github.com/user-attachments/assets/8fe98385-e8bb-493b-86ae-9b8cc2eb803a)


✅ Endpoints REST implementados

| Método | Rota                    | Descrição                      |
| ------ | ----------------------- | ------------------------------ |
| GET    | `/api/persons`          | Lista todas as pessoas         |
| POST   | `/api/persons`          | Adiciona uma nova pessoa       |
| GET    | `/api/persons/{id}/cep` | Consulta o endereço via ViaCEP |

Exemplo de cadastro de pessoa:


![image](https://github.com/user-attachments/assets/796dd3b1-f781-4a3b-ac56-e2b4424bd980)

POST /api/persons
{
  "externalId": 1,
  "imagePath": "caminho/da/imagem.jpg",
  "name": "Guilherme",
  "status": 1,
  "identified": 1,
  "ncep": "17990-005"  (opcional)
}

Resposta:

{
  "id": 1,
  "externalId": 1,
  "imagePath": "caminho/da/imagem.jpg",
  "name": "Guilherme",
  "status": 1,
  "identified": 1,
  "ncep": "17990-005"
}



# ✅ Organização Modular


Serviços independentes:

- ViaCepService: Responsável somente por consumo da API externa.
- PersonService: Lógica relacionada ao CRUD de pessoas.

Reutilizáveis:
- Os serviços podem ser usados em outros projetos.


✅ Separação entre Camadas

- Exemplo de fluxo de uma requisição:
  
Pessoa faz requisição HTTP → Controller → Service → Repository/API → Resposta HTTP


✅ Execução do Projeto
1. Clonar o repositório:

- git clone [https://github.com/GuilhermeSSantos2004/spring-servicearch-api-gs.git](https://github.com/GuilhermeSSantos2004/spring-servicearch-api-gs.git)

2. Executar:
./mvnw spring-boot:run

3. Acessar endpoints em http://localhost:8080/swagger-ui.html

   




