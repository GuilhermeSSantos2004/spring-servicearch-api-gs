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
