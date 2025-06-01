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

Encontrar pessoas em ambientes escuros representa um grande desafio para equipes de resgate e monitoramento, principalmente à noite, quando a baixa visibilidade pode atrasar o socorro. Pensando nisso, este projeto complementa uma solução integrada, onde a identificação automática de pessoas é realizada por sistemas de visão computacional, como o projeto [detecao-pessoas-visao-noturna](https://github.com/GuilhermeSSantos2004/detecao-pessoas-visao-noturna).

### Como funciona na prática

O sistema de IA (por exemplo, usando MediaPipe Pose em Python) processa vídeos e detecta automaticamente pessoas em ambientes noturnos.  
Cada pessoa encontrada é registrada nesta API, que serve como uma base central de informações, recebendo dados como imagem da pessoa, localização aproximada (CEP), status (ex.: viva, não identificada) e outras informações essenciais para buscas e futuras identificações.

---

### 🎯 Objetivos do Projeto

#### Centralização e Organização dos Dados
- Esta API RESTful serve para organizar, centralizar e padronizar as informações de pessoas detectadas.  
- Diversas aplicações – desde softwares de mapeamento 3D até plataformas de monitoramento e análise de resgates – podem consultar, cadastrar, atualizar ou remover registros com facilidade.
- 
#### Facilidade de Consulta e Integração
- Os endpoints REST permitem que diferentes equipes e sistemas façam buscas rápidas, realizem análises ou exportem os dados.  
- A integração com a API pública ViaCEP enriquece o registro ao trazer automaticamente informações detalhadas do endereço a partir do CEP informado.

#### Escalabilidade e Reutilização
- O projeto foi desenvolvido de forma modular, seguindo boas práticas de arquitetura (MVC).  
- Isso permite integrá-lo facilmente em sistemas maiores, como aplicações de Defesa Civil, ONGs, órgãos públicos e plataformas de resgate – todos podem consumir os dados em tempo real, aumentando a eficiência do resgate e o uso inteligente dos recursos.

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


## 🔧 Funcionalidades CRUD implementadas

![image](https://github.com/user-attachments/assets/fe5206a1-cfbd-4479-9c5d-f9a57eed7381)

- **GET** `/api/persons`  
  Lista todas as pessoas cadastradas.

- **POST** `/api/persons`  
  Adiciona uma nova pessoa.

- **GET** `/api/persons/{id}`  
  Consulta os dados de uma pessoa específica.

- **PUT** `/api/persons/{id}`  
  Atualiza os dados de uma pessoa.

- **DELETE** `/api/persons/{id}`  
  Remove uma pessoa do sistema.

- **GET** `/api/persons/{id}/cep`  
  Consulta o endereço da pessoa via API ViaCEP, usando o CEP cadastrado.


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


### Atualizar uma pessoa existente

```http
PUT /api/persons/{id}
Content-Type: application/json
```
## Exemplo de corpo da requisição:
{
  "externalId": 2,
  "imagePath": "imagens/novo_caminho.jpg",
  "name": "Novo Nome",
  "status": 1,
  "identified": 1,
  "ncep": "01234-567"
}

## Deletar uma pessoa

DELETE /api/persons/{id}




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

   




