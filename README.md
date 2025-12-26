# 🦁 SIGZoo - Sistema Integrado de Gestão de Zoológicos

O SIGZoo é uma aplicação web desenvolvida para facilitar a administração e a gestão operacional de zoológicos. O sistema integra o controle de animais, recintos, funcionários e bilheteria em uma interface unificada e responsiva. O sistema é um projeto feito por estudantes com o único objetivo de praticar e aperfeiçoar conhecimentos em desenvolvimento web, Spring e programação.

# 📖 Sobre o Projeto

Este projeto foi desenvolvido como parte de uma atividade acadêmica, com o objetivo de aplicar conceitos avançados de desenvolvimento web com Java e Spring. O sistema resolve problemas comuns de gestão de zoológicos, como o rastreamento de dietas, alocação de animais em recintos adequados e controle de acesso de funcionários.

# 🚀 Funcionalidades Principais

<img width="1919" height="909" alt="Captura de tela 2025-12-26 111927" src="https://github.com/user-attachments/assets/6d1be9c4-04af-4e04-bf68-f1ee5f1bf7a5" />

<img width="1919" height="909" alt="Captura de tela 2025-12-26 112138" src="https://github.com/user-attachments/assets/cb7d0cac-8fe6-41e3-b4d1-f17a8fcdb220" />


### 🐾 Gestão de Animais e Espécies

 - Cadastro completo de animais com origem e dados biológicos.

 - Associação de animais a recintos e veterinários responsáveis.

 - Validação automática de compatibilidade de área (avisa se o recinto é pequeno demais para a espécie).

### 🏞️ Gestão de Recintos

 - Controle de capacidade e status (Aberto, Fechado, Manutenção).

 - Criação Aninhada: Cadastro de Planos de Dieta (Carne/Vegetais) diretamente na criação do recinto.

 - Relacionamento One-to-One com Planos de Dieta (Cascade/OrphanRemoval).

### 🎫 Bilheteria e Visitantes

 - Área Pública: Interface para visitantes comprarem ingressos online.

 - Consulta de Ingressos: Visitantes podem consultar e reagendar visitas usando o ID do ingresso.

 - Gestão administrativa de vendas e cancelamentos.

### 👥 Controle de Acesso (Segurança)

 - Autenticação via Spring Security.

 - Perfis de usuário com permissões granulares:

 - ADMIN: Acesso total (CRUDs, Deleção, Relatórios).

 - CURADOR: Gestão de recintos e animais.

 - GERENTE_OPERACOES: Gestão de operações relacionadas aos funcionários do zoológico.

### 📊 Dashboard

Painel administrativo com cards de métricas em tempo real (Total de animais, recintos, ingressos vendidos, etc.).

# 🛠 Tecnologias Utilizadas

### Backend

 - Java 17: Linguagem base.

 - Spring Boot 3: Framework principal.

 - Spring Data JPA: Camada de persistência e abstração de banco de dados.

 - Spring Security: Autenticação e autorização.

### Frontend

 - Thymeleaf: Motor de templates para renderização server-side.

 - Bootstrap 5: Framework CSS para layout responsivo e componentes visuais.

 - JavaScript (Vanilla): Lógica de interação no cliente (Fetch API para comunicação com o backend).

 - FontAwesome: Ícones.

### Banco de Dados

 - H2 Database: Banco em memória para desenvolvimento e testes rápidos.

# 🏗 Arquitetura e Design

O projeto segue o padrão MVC (Model-View-Controller) e utiliza DTOs (Data Transfer Objects) para desacoplar a camada de persistência da camada de apresentação/API.

 - Controller: Gerencia as requisições HTTP (separado em PageController para HTML e ApiController para JSON).

 - Service: Contém toda a lógica de negócio e regras de validação.

 - Repository: Interfaces JPA para comunicação com o banco.

 - Model/Entity: Representação das tabelas do banco.

# ▶️ Como Executar

- Clone o repositório:

- Execute a aplicação:

  Via Maven Wrapper (Linux/Mac):
    ```bash
      ./mvnw spring-boot:run
    ```
    
  Via Maven Wrapper (Windows):
    ```bash
      .\mvnw.cmd spring-boot:run
    ```
    
- Acesse no Navegador:

  - Aplicação: http://localhost:8080

  - Console H2 (Banco de Dados): http://localhost:8080/h2-console

    - JDBC URL: jdbc:h2:file:./sigzoo 
    
    - User: Zoo
    
    - Password: (vazio)
