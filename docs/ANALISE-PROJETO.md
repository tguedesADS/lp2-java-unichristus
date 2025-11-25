# Análise do Projeto de Extensão - Sistema de Gestão para ILPI

## 📋 Índice
1. [Visão Geral](#visão-geral)
2. [Como Instalar e Executar](#como-instalar-e-executar)
3. [Arquitetura do Sistema](#arquitetura-do-sistema)
4. [O Que Já Foi Implementado](#o-que-já-foi-implementado)
5. [O Que Está Faltando](#o-que-está-faltando)
6. [Especificações Detalhadas dos Módulos Pendentes](#especificações-detalhadas-dos-módulos-pendentes)
7. [Próximos Passos](#próximos-passos)

---

## 🎯 Visão Geral

### Contexto do Projeto

Este é um sistema desktop desenvolvido em **Java 17** com **Spring Boot 3.5.0** para a **Casa de Repouso Recanto do Sagrado Coração (RSC)**, uma Instituição de Longa Permanência para Idosos (ILPI) localizada em Fortaleza/CE.

**Cliente:** Associação de Assistência Social Catarina Laboure – Recanto do Sagrado Coração  
**CNPJ:** 07.370.422/0001-06  
**Endereço:** Avenida da Universidade, 3106. Benfica  
**Responsável Técnico:** Fabiana Ribeiro Sampaio Bündchen  
**Capacidade:** ~35 residentes idosas

### Objetivo do Sistema

Centralizar e gerenciar informações de saúde, histórico familiar, visitas, consultas e acompanhamento das residentes, oferecendo eficiência e segurança no gerenciamento dos dados. O sistema substitui processos manuais e dados dispersos que levavam a erros e perda de informações.

### Equipe Anterior (2025-1)

**Equipe:** Java Minds  
- Nívea Monteiro (Project Manager)
- Gustavo Cavalcante (Back-end)
- Rosyane Íris (Back-end)
- Giulie Ribeiro (UX/UI)
- Pollyanna (Front-end)
- Gabriel Câmara (Front-end)

**Repositórios:**
- Back-end: https://github.com/Gustavotcsi/Projeto
- Front-end: https://github.com/pollymelo/front-java
- Protótipo Figma: https://www.figma.com/design/G9bJF5PT1qqwb1IkxEyaa1/Recanto-do-sagrado-coração

### Prazo de Entrega

**Data de Entrega:** 10/12/2025  
**Apresentação:** 10/12/2025

---

## 🚀 Como Instalar e Executar

### Pré-requisitos

1. **Java 17** (JDK 17 ou superior)
   - Verificar instalação: `java -version`
   - Download: https://www.oracle.com/java/technologies/downloads/

2. **Maven** (geralmente já vem com IDEs modernas)
   - Verificar instalação: `mvn -version`
   - O projeto inclui Maven Wrapper (`mvnw.cmd` para Windows)

3. **MySQL** (versão 8.0 ou superior)
   - Instalar MySQL Server
   - Criar banco de dados: `CREATE DATABASE projetoextensao;`

4. **IDE recomendada:** IntelliJ IDEA, Eclipse ou VS Code com extensões Java

### Configuração do Banco de Dados

1. Abrir o arquivo `src/main/resources/application.properties`
2. Ajustar as credenciais do MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/projetoextensao
spring.datasource.username=root
spring.datasource.password=123456  # ALTERAR para sua senha do MySQL
```

### Executar o Projeto

**Opção 1 - Via Maven Wrapper (Windows):**
```cmd
cd Projeto-main
mvnw.cmd spring-boot:run
```

**Opção 2 - Via Maven instalado:**
```cmd
cd Projeto-main
mvn spring-boot:run
```

**Opção 3 - Via IDE:**
- Abrir o projeto na IDE
- Executar a classe `ProjetoApplication.java`
- A aplicação iniciará e abrirá a tela de login automaticamente

### Credenciais Padrão
```
Usuário: admin
Senha: 1234
```
*(Configurado em `application.properties`)*

---

## 🏗️ Arquitetura do Sistema

### Stack Tecnológica

- **Backend:** Spring Boot 3.5.0
- **Linguagem:** Java 17
- **Interface Gráfica:** Java Swing
- **Banco de Dados:** MySQL
- **ORM:** Spring Data JPA / Hibernate
- **Gerenciamento de Dependências:** Maven
- **Utilitários:** Lombok (redução de boilerplate)

### Estrutura de Pacotes

```
com.ProjetoExtensao.Projeto/
├── Config/              # Configurações (DatabaseConfig)
├── infra/               # Infraestrutura e utilitários
│   ├── Cores.java       # Paleta de cores da UI
│   ├── DateTimeFormatter.java
│   ├── IconManager.java # Gerenciamento de ícones
│   └── PanelsFactory.java # Factory para painéis reutilizáveis
├── models/              # Entidades JPA
│   ├── Paciente.java
│   ├── Consulta.java
│   ├── ResponsavelSaude.java
│   └── TipoConsulta.java (enum)
├── repositorios/        # Repositórios Spring Data JPA
│   ├── PacienteRepositorio.java
│   ├── ConsultaRepositorio.java
│   └── ResponsavelRepositorio.java
├── servicos/            # Lógica de negócio
│   ├── PacienteService.java
│   ├── ConsultaService.java
│   ├── ResponsavelService.java
│   └── NavigationService.java # Navegação entre telas
└── view/                # Telas Swing
    ├── TelaLogin.java
    ├── TelaGeral.java   # Dashboard principal
    ├── TelaPacientes.java
    ├── TelaCadastroPacientes.java
    ├── TelaConsultas.java
    └── TelaAgendamentoConsulta.java
```

### Padrão Arquitetural

O projeto segue uma **arquitetura em camadas**:

1. **Camada de Apresentação (View):** Telas Swing
2. **Camada de Serviço (Service):** Lógica de negócio
3. **Camada de Repositório (Repository):** Acesso a dados
4. **Camada de Modelo (Model):** Entidades JPA

### Recursos da Interface

- **Ícones disponíveis:** admin, consultas, documentos, eventos, exit, familia, pacientes, prontuarios, refresh, relatorios, vacinas
- **Paleta de cores personalizada** (definida em `Cores.java`)
- **Componentes reutilizáveis** via `PanelsFactory`

---

## ✅ O Que Já Foi Implementado (Equipe Anterior - 2025-1)

### 1. Infraestrutura Base
- ✅ Configuração Spring Boot completa com arquitetura MVC
- ✅ Conexão com banco de dados MySQL
- ✅ Spring Data JPA para persistência
- ✅ Sistema de navegação entre telas (NavigationService)
- ✅ Gerenciamento de ícones e cores (IconManager, Cores)
- ✅ Factory para componentes UI reutilizáveis (PanelsFactory)
- ✅ Tratamento centralizado de exceções
- ✅ Injeção de dependências

### 2. Módulo de Login
- ✅ **Tela de Login:** `TelaLogin.java`
- ✅ Autenticação de usuários
- ✅ Controle de acesso ao sistema
- ✅ Credenciais básicas configuradas (admin/1234)

### 3. Painel Administrativo (Dashboard)
- ✅ **TelaGeral.java** - Visão geral com indicadores:
  - Estatísticas: Pacientes (100), Enfermaria (20), Visitas (2)
  - Botões para 8 módulos principais
  - Layout responsivo com GridLayout
  - Ícones personalizados para cada módulo

### 4. Módulo de Gestão de Pacientes (Idosas)
- ✅ **Modelo de dados:** Entidade `Paciente` com campos obrigatórios:
  - Nome completo
  - CPF (único)
  - Data de nascimento
  - Nome da mãe
  - Cartão do SUS (único)
  - Data de entrada na casa
  - Relacionamento OneToMany com Consultas
- ✅ **Repositório:** `PacienteRepositorio` (CRUD completo)
- ✅ **Serviço:** `PacienteService` (lógica de negócio)
- ✅ **Telas:**
  - `TelaPacientes.java` - Listagem e visualização de pacientes
  - `TelaCadastroPacientes.java` - Cadastro e edição de dados das residentes

### 5. Módulo de Consultas
- ✅ **Modelo de dados:** Entidade `Consulta` com campos:
  - Data e hora
  - Tipo de consulta (enum: rotina, emergência, especializada)
  - Motivo da consulta
  - Diagnóstico (CID-10)
  - Anotações do profissional
  - Relacionamento ManyToOne com Paciente
  - Relacionamento ManyToOne com ResponsavelSaude
- ✅ **Repositório:** `ConsultaRepositorio`
- ✅ **Serviço:** `ConsultaService` (lógica de negócio)
- ✅ **Telas:**
  - `TelaConsultas.java` - Lançamento e consulta de consultas médicas
  - `TelaAgendamentoConsulta.java` - Agendamento de novas consultas
- ✅ **Funcionalidades:**
  - Registrar diagnóstico
  - Gerar encaminhamento para exame/especialista

### 6. Módulo de Responsáveis de Saúde (Profissionais)
- ✅ **Modelo de dados:** Entidade `ResponsavelSaude` com campos:
  - Email (único)
  - Senha
  - Nome completo
  - Relacionamento OneToMany com Consultas
- ✅ **Repositório:** `ResponsavelRepositorio`
- ✅ **Serviço:** `ResponsavelService`

### 7. Módulos Parcialmente Implementados

#### Prontuários (Parcial)
- ✅ Lógica de negócio implementada no back-end
- ✅ Estrutura de dados criada
- ❌ Interface gráfica não implementada

#### Gestão de Familiares (Parcial)
- ✅ Lógica de negócio implementada no back-end
- ✅ Estrutura de dados criada
- ❌ Interface gráfica não implementada

#### Vacinação (Parcial)
- ✅ Parte da lógica implementada no back-end
- ❌ Interface gráfica não implementada

#### Eventos Sentinela (Parcial)
- ✅ Parte da lógica implementada no back-end
- ❌ Interface gráfica não implementada

### 8. Documentação Entregue
- ✅ Diagrama de classes UML (junho 2025)
- ✅ Documentação técnica completa
- ✅ Protótipo visual no Figma
- ✅ Repositórios GitHub organizados

---

## ❌ O Que Está Faltando

### Resumo Executivo

Dos 9 módulos planejados, **3 estão completos** (Login, Painel, Pacientes, Consultas), **4 estão parcialmente implementados** no back-end (Prontuários, Familiares, Vacinas, Eventos Sentinelas) e **2 não foram iniciados** (Documentos, Relatórios).

### Módulos Que Precisam Ser Finalizados

#### 1. 📋 Módulo Prontuários Médicos (ALTA PRIORIDADE)

**Status:** Back-end parcialmente implementado | Front-end não implementado

**Requisitos Obrigatórios (Especificação do Professor):**
- [ ] **Entidade `ProntuarioMedico`** com atributos:
  - ID do prontuário (gerado automaticamente)
  - Associação à classe Paciente
  - Lista de consultas (associação à classe Consulta)
  - Lista de prescrições (associação à classe Prescrição)
  - Lista de exames solicitados/resultados (associação à classe Exame)
  - Histórico de internações (lista de strings ou classe específica)
  - Histórico de vacinação (lista de objetos Vacina)

- [ ] **Métodos obrigatórios:**
  - Adicionar nova consulta
  - Vincular resultado de exame
  - Gerar resumo do histórico (para relatórios)
  - Buscar consultas por data/profissional

- [ ] **Telas necessárias:**
  - Visualização completa do prontuário
  - Edição de informações
  - Histórico de atendimentos
  - Integração com módulo de Consultas

#### 2. 👨‍👩‍👧 Módulo Gestão de Familiares (ALTA PRIORIDADE)

**Status:** Back-end parcialmente implementado | Front-end não implementado

**Funcionalidades:**
- [ ] Finalizar entidade `Familiar` (nome, parentesco, contato, CPF)
- [ ] Relacionamento OneToMany com `Paciente`
- [ ] **Telas necessárias:**
  - Registro de responsáveis
  - Vínculos com as idosas
  - Contatos de emergência
  - Listagem e edição de familiares

#### 3. ⚠️ Módulo Eventos Sentinelas (CRÍTICO - RDC/ANVISA)

**Status:** Back-end parcialmente implementado | Front-end não implementado

**Referência:** RDC/ANVISA Nº 502 DE 27 DE MAIO DE 2021

**Eventos que devem ser registrados (Check-list diário):**
- [ ] Tentativa de suicídio
- [ ] Quedas
- [ ] Diarreia (doença diarreica aguda)
- [ ] Escabiose
- [ ] Desidratação
- [ ] Úlcera por pressão (úlcera de decúbito)
- [ ] Desnutrição
- [ ] Óbito

**Dados vitais diários:**
- [ ] Pressão arterial
- [ ] Glicemia
- [ ] Temperatura

**Entidade `EventoSentinela`:**
- [ ] Tipo de evento (enum com os 8 tipos acima)
- [ ] Data/hora da ocorrência
- [ ] Residente (relacionamento com Paciente)
- [ ] Descrição detalhada
- [ ] Gravidade
- [ ] Ações tomadas
- [ ] Responsável pelo registro

**Telas necessárias:**
- [ ] Check-list diário de plantão (formato tabela mensal)
- [ ] Registro de novo evento
- [ ] Histórico de eventos por residente
- [ ] Dashboard com indicadores (integrado com Relatórios)

#### 4. 💉 Módulo Controle de Vacinas (ALTA PRIORIDADE)

**Status:** Back-end parcialmente implementado | Front-end não implementado

**Requisitos Obrigatórios:**
- [ ] **Entidade `Vacina`** com atributos:
  - Data da aplicação da vacina
  - Identificação da vacina (nome)
  - Lote
  - Fabricante
  - Profissional aplicador
  - Próxima dose (se aplicável)
  - Relacionamento com Paciente

**Funcionalidades:**
- [ ] Registro de vacinação
- [ ] Histórico vacinal das residentes
- [ ] Calendário vacinal para idosos
- [ ] Alertas de vacinas pendentes/atrasadas
- [ ] Percentual de vacinação por tipo de vacina

**Telas necessárias:**
- [ ] Cartão de vacinação individual
- [ ] Registro de nova dose
- [ ] Consulta de histórico vacinal
- [ ] Relatório de cobertura vacinal

#### 5. 📄 Módulo Documentos (MÉDIA PRIORIDADE)

**Status:** Não implementado

**Funcionalidades:**
- [ ] **Entidade `Documento`** com atributos:
  - Tipo de documento (RG, CPF, certidões, laudos, etc.)
  - Número do documento
  - Data de emissão
  - Arquivo digitalizado (caminho/blob)
  - Relacionamento com Paciente

- [ ] Upload e armazenamento de arquivos
- [ ] Visualização de documentos
- [ ] Download de documentos
- [ ] Organização por tipo

**Telas necessárias:**
- [ ] Upload e visualização de arquivos/documentos
- [ ] Listagem de documentos por residente
- [ ] Gerenciamento de documentos

#### 6. 📊 Módulo Relatórios (CRÍTICO - RDC/ANVISA)

**Status:** Não implementado

**Requisitos Obrigatórios (Especificação do Professor):**

**Relatório Individual por Residente:**
- [ ] Dados pessoais da idosa (idade, número do cartão do SUS)
- [ ] Informações mais recentes
- [ ] Medicamentos em uso atualmente
- [ ] Vacinas já tomadas
- [ ] Histórico de eventos sentinelas

**Relatórios Estatísticos:**
- [ ] Percentual de vacinação para uma determinada vacina
- [ ] Percentual de idosas que tiveram algum tipo de incidente (Eventos Sentinelas)

**Indicadores ANVISA (RDC 502/2021) - Consolidado Mensal:**

1. **Taxa de mortalidade (%)**
   - Fórmula: (Nº de óbitos no mês / Nº de residentes no mês) × 100

2. **Taxa de incidência de doença diarreica aguda (%)**
   - Fórmula: (Novos casos de diarreia no mês / Nº de residentes no mês) × 100

3. **Taxa de incidência de escabiose (%)**
   - Fórmula: (Novos casos de escabiose no mês / Nº de residentes no mês) × 100

4. **Taxa de incidência de desidratação (%)**
   - Fórmula: (Casos de desidratação no mês / Nº de residentes no mês) × 100

5. **Taxa de prevalência de úlcera de decúbito (%)**
   - Fórmula: (Residentes com úlcera no mês / Nº de residentes no mês) × 100

6. **Taxa de prevalência de desnutrição (%)**
   - Fórmula: (Residentes com desnutrição no mês / Nº de residentes no mês) × 100

**Funcionalidades:**
- [ ] Relatórios dinâmicos gerados a partir dos registros
- [ ] Filtros por período (mensal, trimestral, anual)
- [ ] Exportação para PDF
- [ ] Exportação para Excel
- [ ] Gráficos e visualizações

**Telas necessárias:**
- [ ] Seleção de tipo de relatório
- [ ] Filtros e parâmetros
- [ ] Visualização de relatórios
- [ ] Dashboard de indicadores ANVISA

### Melhorias Necessárias nos Módulos Existentes

#### Segurança (CRÍTICO)
- [ ] Implementar autenticação real (Spring Security)
- [ ] Hash de senhas (BCrypt) - **SENHAS EM TEXTO PLANO ATUALMENTE**
- [ ] Controle de acesso por perfis (Admin, Enfermeiro, Médico, Assistente Social)
- [ ] Sessão de usuário com timeout
- [ ] Logs de auditoria (quem fez o quê e quando)

#### Validações (ALTA PRIORIDADE)
- [ ] Validação de CPF (algoritmo de dígitos verificadores)
- [ ] Validação de Cartão SUS (15 dígitos)
- [ ] Validação de datas (não permitir datas futuras onde não faz sentido)
- [ ] Validação de campos obrigatórios na UI
- [ ] Validação de unicidade (CPF e Cartão SUS únicos)
- [ ] Validação de idade mínima (60 anos para ILPI)

#### Funcionalidades Gerais
- [ ] Sistema de busca/filtro em todas as listagens
- [ ] Paginação nas tabelas (atualmente sem limite)
- [ ] Confirmação antes de excluir registros
- [ ] Mensagens de sucesso/erro mais claras e padronizadas
- [ ] Tratamento de erros de conexão com banco de dados
- [ ] Backup e restauração de dados

#### Interface (UX/UI)
- [ ] Melhorar responsividade das telas
- [ ] Adicionar tooltips nos botões
- [ ] Implementar atalhos de teclado
- [ ] Melhorar feedback visual (loading, progress bars)
- [ ] Padronizar espaçamentos e fontes
- [ ] Adicionar ícones nos botões de ação

#### Banco de Dados
- [ ] Scripts de migração (Flyway ou Liquibase)
- [ ] Dados iniciais (seed data) - usuário admin, tipos de consulta, etc.
- [ ] Índices para otimização de consultas
- [ ] Backup automático diário
- [ ] Documentação do modelo de dados

#### Integração entre Módulos
- [ ] Prontuário deve consolidar dados de Consultas, Vacinas e Eventos
- [ ] Relatórios devem consumir dados de todos os módulos
- [ ] Dashboard deve exibir estatísticas reais do banco de dados

---

## 📐 Especificações Detalhadas dos Módulos Pendentes

### 1. Especificação: Módulo Prontuários Médicos

#### Modelo de Dados

```java
@Entity
@Table(name = "prontuarios_medicos")
public class ProntuarioMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "paciente_id", nullable = false, unique = true)
    private Paciente paciente;
    
    @OneToMany(mappedBy = "prontuario")
    private List<Consulta> consultas;
    
    @OneToMany(mappedBy = "prontuario")
    private List<Prescricao> prescricoes;
    
    @OneToMany(mappedBy = "prontuario")
    private List<Exame> exames;
    
    @ElementCollection
    private List<String> historicoInternacoes;
    
    @OneToMany(mappedBy = "prontuario")
    private List<Vacina> historicoVacinacao;
    
    @Column(columnDefinition = "TEXT")
    private String observacoesGerais;
    
    @Column
    private LocalDateTime dataUltimaAtualizacao;
    
    // Métodos obrigatórios
    public void adicionarConsulta(Consulta consulta);
    public void vincularResultadoExame(Exame exame);
    public String gerarResumoHistorico();
    public List<Consulta> buscarConsultasPorData(LocalDate inicio, LocalDate fim);
    public List<Consulta> buscarConsultasPorProfissional(ResponsavelSaude profissional);
}
```

#### Entidades Relacionadas Necessárias

**Prescrição:**
```java
@Entity
public class Prescricao {
    private Long id;
    private LocalDate dataPrescricao;
    private String medicamento;
    private String dosagem;
    private String frequencia;
    private String duracao;
    private ResponsavelSaude profissionalPrescritor;
    private ProntuarioMedico prontuario;
    private boolean ativa; // true se ainda está em uso
}
```

**Exame:**
```java
@Entity
public class Exame {
    private Long id;
    private String tipoExame;
    private LocalDate dataSolicitacao;
    private LocalDate dataResultado;
    private String resultado;
    private String arquivo; // caminho para PDF/imagem
    private ResponsavelSaude profissionalSolicitante;
    private ProntuarioMedico prontuario;
}
```

#### Telas Necessárias

1. **TelaProntuario.java** - Visualização completa
   - Dados do paciente (header)
   - Abas: Consultas | Prescrições | Exames | Internações | Vacinas
   - Botão "Gerar Relatório Completo"

2. **TelaAdicionarPrescricao.java** - Formulário de prescrição

3. **TelaAdicionarExame.java** - Registro de exame

---

### 2. Especificação: Módulo Eventos Sentinelas

#### Modelo de Dados

```java
@Entity
@Table(name = "eventos_sentinelas")
public class EventoSentinela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEvento tipoEvento;
    
    @Column(nullable = false)
    private LocalDate dataOcorrencia;
    
    @Column(nullable = false)
    private LocalTime horaOcorrencia;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    private Gravidade gravidade; // LEVE, MODERADA, GRAVE, CRITICA
    
    @Column(columnDefinition = "TEXT")
    private String acoesTomadas;
    
    @ManyToOne
    @JoinColumn(name = "responsavel_registro_id")
    private ResponsavelSaude responsavelRegistro;
    
    @Column
    private LocalDateTime dataHoraRegistro;
}

public enum TipoEvento {
    TENTATIVA_SUICIDIO("Tentativa de suicídio"),
    QUEDA("Quedas"),
    DIARREIA("Diarreia"),
    ESCABIOSE("Escabiose"),
    DESIDRATACAO("Desidratação"),
    ULCERA_PRESSAO("Úlcera por pressão"),
    DESNUTRICAO("Desnutrição"),
    OBITO("Óbito");
    
    private String descricao;
}
```

#### Modelo para Dados Vitais Diários

```java
@Entity
@Table(name = "dados_vitais")
public class DadosVitais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @Column(nullable = false)
    private LocalDate data;
    
    @Column(nullable = false)
    private LocalTime hora;
    
    private String pressaoArterial; // ex: "120/80"
    private Integer glicemia; // mg/dL
    private Double temperatura; // °C
    
    @ManyToOne
    private ResponsavelSaude responsavelMedicao;
}
```

#### Telas Necessárias

1. **TelaChecklistPlantao.java** - Check-list mensal
   - Tabela com dias do mês (1-31) nas colunas
   - Linhas: cada tipo de evento + dados vitais
   - Células clicáveis para registrar ocorrência
   - Filtro por residente e mês/ano

2. **TelaRegistroEvento.java** - Registro detalhado de evento
   - Formulário completo com todos os campos
   - Upload de fotos (para quedas, úlceras)

3. **TelaHistoricoEventos.java** - Histórico por residente
   - Listagem cronológica
   - Filtros por tipo e período

---

### 3. Especificação: Módulo Relatórios

#### Tipos de Relatórios

**1. Relatório Individual de Residente**
```java
public class RelatorioIndividualDTO {
    // Dados Pessoais
    private String nomeCompleto;
    private String cpf;
    private String cartaoSUS;
    private int idade;
    private LocalDate dataEntrada;
    private int diasNaInstituicao;
    
    // Medicamentos Atuais
    private List<PrescricaoAtivaDTO> medicamentosEmUso;
    
    // Vacinas
    private List<VacinaDTO> vacinasAplicadas;
    private List<String> vacinasPendentes;
    
    // Eventos Sentinelas
    private int totalEventos;
    private Map<TipoEvento, Integer> eventosPorTipo;
    private EventoSentinela ultimoEvento;
    
    // Consultas Recentes
    private List<ConsultaResumoDTO> ultimasConsultas;
}
```

**2. Relatório de Indicadores ANVISA (Mensal)**
```java
public class RelatorioIndicadoresAnvisaDTO {
    private int mes;
    private int ano;
    private int numeroResidentes;
    
    // Indicadores
    private double taxaMortalidade;
    private double taxaDoencaDiarreica;
    private double taxaEscabiose;
    private double taxaDesidratacao;
    private double taxaUlceraDecubito;
    private double taxaDesnutricao;
    
    // Detalhamento
    private int numeroObitos;
    private int casosDiarreia;
    private int casosEscabiose;
    private int casosDesidratacao;
    private int casosUlcera;
    private int casosDesnutricao;
}
```

**3. Relatório de Cobertura Vacinal**
```java
public class RelatorioCoberturaVacinalDTO {
    private String nomeVacina;
    private int totalResidentes;
    private int residentesVacinadas;
    private double percentualCobertura;
    private List<PacienteDTO> residentesPendentes;
}
```

#### Service de Relatórios

```java
@Service
public class RelatorioService {
    
    public RelatorioIndividualDTO gerarRelatorioIndividual(Long pacienteId);
    
    public RelatorioIndicadoresAnvisaDTO gerarIndicadoresAnvisa(int mes, int ano);
    
    public RelatorioCoberturaVacinalDTO gerarCoberturaVacinal(String nomeVacina);
    
    public byte[] exportarParaPDF(Object relatorio);
    
    public byte[] exportarParaExcel(Object relatorio);
    
    public Map<String, Object> calcularEstatisticasGerais();
}
```

#### Telas Necessárias

1. **TelaRelatorios.java** - Menu de relatórios
   - Botões para cada tipo de relatório
   - Filtros de período

2. **TelaRelatorioIndividual.java** - Visualização do relatório individual
   - Seleção de residente
   - Botões: Visualizar | Exportar PDF | Imprimir

3. **TelaIndicadoresAnvisa.java** - Dashboard de indicadores
   - Gráficos de linha (evolução mensal)
   - Tabela consolidada
   - Exportação para Excel

4. **TelaCoberturaVacinal.java** - Relatório de vacinas
   - Seleção de vacina
   - Gráfico de pizza (cobertura)
   - Lista de pendentes

---

### 4. Especificação: Módulo Vacinas

#### Modelo de Dados

```java
@Entity
@Table(name = "vacinas")
public class Vacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @Column(nullable = false)
    private String nomeVacina;
    
    @Column(nullable = false)
    private LocalDate dataAplicacao;
    
    @Column
    private String lote;
    
    @Column
    private String fabricante;
    
    @ManyToOne
    @JoinColumn(name = "profissional_aplicador_id")
    private ResponsavelSaude profissionalAplicador;
    
    @Column
    private LocalDate proximaDose;
    
    @Column
    private Integer numeroDose; // 1ª, 2ª, 3ª dose, reforço
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;
    
    @ManyToOne
    @JoinColumn(name = "prontuario_id")
    private ProntuarioMedico prontuario;
}
```

#### Calendário Vacinal para Idosos (Referência)

- Influenza (Gripe) - Anual
- Pneumocócica 23-valente (Pneumo23) - Dose única + reforço após 5 anos
- Hepatite B - 3 doses
- dT (Difteria e Tétano) - Reforço a cada 10 anos
- Herpes Zóster - 2 doses
- COVID-19 - Conforme protocolo vigente

#### Telas Necessárias

1. **TelaCartaoVacinal.java** - Cartão de vacinação individual
   - Seleção de residente
   - Tabela com histórico de vacinas
   - Alertas de vacinas pendentes/atrasadas
   - Botão "Registrar Nova Dose"

2. **TelaRegistroVacina.java** - Formulário de registro
   - Campos: vacina, data, lote, fabricante, dose
   - Cálculo automático de próxima dose

3. **TelaCalendarioVacinal.java** - Visão geral
   - Lista de todas as residentes
   - Status de cada vacina (✓ em dia | ⚠ pendente | ✗ atrasada)

---

### 5. Especificação: Módulo Familiares

#### Modelo de Dados

```java
@Entity
@Table(name = "familiares")
public class Familiar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @Column(nullable = false)
    private String nomeCompleto;
    
    @Column(nullable = false)
    private String cpf;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Parentesco parentesco;
    
    @Column
    private String telefone;
    
    @Column
    private String celular;
    
    @Column
    private String email;
    
    @Column
    private String endereco;
    
    @Column
    private boolean contatoEmergencia;
    
    @Column
    private boolean responsavelLegal;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;
}

public enum Parentesco {
    FILHO_A("Filho(a)"),
    NETO_A("Neto(a)"),
    IRMAO_A("Irmão(ã)"),
    SOBRINHO_A("Sobrinho(a)"),
    PRIMO_A("Primo(a)"),
    OUTRO("Outro");
}
```

#### Telas Necessárias

1. **TelaFamiliares.java** - Listagem de familiares
   - Filtro por residente
   - Indicação visual de contato de emergência
   - Botões: Adicionar | Editar | Remover

2. **TelaCadastroFamiliar.java** - Formulário
   - Todos os campos
   - Checkbox: Contato de Emergência | Responsável Legal

---

### 6. Especificação: Módulo Documentos

#### Modelo de Dados

```java
@Entity
@Table(name = "documentos")
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDocumento tipoDocumento;
    
    @Column
    private String numeroDocumento;
    
    @Column
    private LocalDate dataEmissao;
    
    @Column(nullable = false)
    private String nomeArquivo;
    
    @Column(nullable = false)
    private String caminhoArquivo;
    
    @Column
    private Long tamanhoBytes;
    
    @Column
    private String extensao; // pdf, jpg, png
    
    @Column
    private LocalDateTime dataUpload;
    
    @ManyToOne
    @JoinColumn(name = "usuario_upload_id")
    private ResponsavelSaude usuarioUpload;
}

public enum TipoDocumento {
    RG("RG"),
    CPF("CPF"),
    CERTIDAO_NASCIMENTO("Certidão de Nascimento"),
    CARTAO_SUS("Cartão do SUS"),
    LAUDO_MEDICO("Laudo Médico"),
    EXAME("Exame"),
    RECEITA("Receita Médica"),
    TERMO_RESPONSABILIDADE("Termo de Responsabilidade"),
    OUTRO("Outro");
}
```

#### Telas Necessárias

1. **TelaDocumentos.java** - Gerenciamento de documentos
   - Listagem por residente
   - Filtro por tipo
   - Botões: Upload | Visualizar | Download | Excluir

2. **TelaUploadDocumento.java** - Upload de arquivo
   - Seleção de arquivo (JFileChooser)
   - Tipo de documento
   - Número e data (se aplicável)

3. **TelaVisualizadorDocumento.java** - Visualização
   - Exibição de PDF/imagem
   - Botão de impressão

---

## 🎯 Próximos Passos Recomendados

### Fase 1: Consolidação e Preparação (1-2 semanas)

**Prioridade: CRÍTICA**

1. **Revisar código existente**
   - Executar o projeto e testar módulos implementados
   - Identificar bugs e problemas
   - Verificar integração com banco de dados

2. **Implementar validações básicas**
   - Validação de CPF
   - Validação de Cartão SUS
   - Validação de campos obrigatórios

3. **Melhorar segurança**
   - Implementar hash de senhas (BCrypt)
   - Adicionar Spring Security básico
   - Criar perfis de usuário

4. **Preparar estrutura para novos módulos**
   - Criar pacotes vazios
   - Definir interfaces
   - Preparar repositórios base

### Fase 2: Módulos Críticos - Compliance ANVISA (2-3 semanas)

**Prioridade: ALTA - Obrigatório para funcionamento da ILPI**

**Ordem de implementação:**

1. **Eventos Sentinelas** (1 semana)
   - Entidades: EventoSentinela, DadosVitais
   - Repositórios e Services
   - Telas: Check-list, Registro, Histórico
   - **Justificativa:** Exigência RDC/ANVISA 502/2021

2. **Prontuários Médicos** (1 semana)
   - Entidades: ProntuarioMedico, Prescricao, Exame
   - Integração com Consultas existentes
   - Telas: Visualização, Prescrições, Exames
   - **Justificativa:** Requisito obrigatório do professor

3. **Relatórios ANVISA** (1 semana)
   - Service de cálculo de indicadores
   - Telas: Dashboard ANVISA, Exportação
   - Integração com Eventos Sentinelas
   - **Justificativa:** Compliance regulatório

### Fase 3: Módulos de Gestão (2 semanas)

**Prioridade: ALTA**

1. **Gestão de Familiares** (3-4 dias)
   - Finalizar back-end existente
   - Telas: Listagem, Cadastro
   - Contatos de emergência
   - **Justificativa:** Importante para comunicação

2. **Controle de Vacinas** (3-4 dias)
   - Finalizar back-end existente
   - Telas: Cartão vacinal, Registro
   - Alertas de vacinas pendentes
   - **Justificativa:** Controle sanitário obrigatório

3. **Relatórios Gerenciais** (3-4 dias)
   - Relatório individual de residente
   - Relatório de cobertura vacinal
   - Exportação PDF/Excel
   - **Justificativa:** Requisito do professor

### Fase 4: Módulos Complementares (1 semana)

**Prioridade: MÉDIA**

1. **Documentos** (1 semana)
   - Entidade e repositório
   - Upload de arquivos
   - Telas: Gerenciamento, Visualização
   - **Justificativa:** Organização administrativa

### Fase 5: Refinamento e Entrega (1 semana)

**Prioridade: ALTA**

1. **Testes e correções** (2-3 dias)
   - Testar todos os módulos
   - Corrigir bugs encontrados
   - Validar integração entre módulos

2. **Melhorias de interface** (1-2 dias)
   - Padronizar telas
   - Melhorar feedback visual
   - Adicionar tooltips e ajuda

3. **Documentação** (1-2 dias)
   - Atualizar README
   - Documentar código
   - Criar manual de usuário básico

4. **Preparar apresentação** (1 dia)
   - Slides conforme template do professor
   - Demonstração prática
   - Vídeo de apresentação (se necessário)

---

## 📊 Cronograma Sugerido (até 10/12/2025)

| Semana | Período | Atividades | Entregáveis |
|--------|---------|------------|-------------|
| 1 | 21-27/11 | Fase 1: Consolidação | Código revisado, validações, segurança |
| 2 | 28/11-04/12 | Fase 2: Eventos Sentinelas + Prontuários | 2 módulos funcionais |
| 3 | 05-11/12 | Fase 2: Relatórios ANVISA + Fase 3: Familiares/Vacinas | 3 módulos funcionais |
| 4 | 12-18/12 | Fase 4: Documentos + Fase 5: Refinamento | Sistema completo |
| 5 | 19-25/12 | Fase 5: Testes finais + Apresentação | Entrega final |

**Data de Entrega:** 10/12/2025  
**Apresentação:** 10/12/2025

---

## ⚠️ Riscos e Mitigações

### Riscos Identificados

1. **Prazo apertado** (até 10/12/2025)
   - **Mitigação:** Focar em módulos críticos primeiro, deixar Documentos como opcional

2. **Complexidade dos Relatórios ANVISA**
   - **Mitigação:** Usar bibliotecas prontas (JasperReports, Apache POI)

3. **Integração entre módulos**
   - **Mitigação:** Definir interfaces claras desde o início

4. **Falta de dados de teste**
   - **Mitigação:** Criar script de seed data com dados fictícios

5. **Problemas com upload de arquivos**
   - **Mitigação:** Usar armazenamento local simples (pasta no servidor)

### Priorização em Caso de Atraso

**Módulos OBRIGATÓRIOS (não podem faltar):**
- ✅ Login (já implementado)
- ✅ Pacientes (já implementado)
- ✅ Consultas (já implementado)
- 🔴 Eventos Sentinelas (ANVISA)
- 🔴 Prontuários (requisito professor)
- 🔴 Relatórios ANVISA (compliance)

**Módulos IMPORTANTES (implementar se possível):**
- 🟡 Vacinas
- 🟡 Familiares
- 🟡 Relatórios Gerenciais

**Módulos OPCIONAIS (podem ficar para depois):**
- 🟢 Documentos

---

---

## 📚 Documentos de Referência

### Documentação do Projeto

1. **PROJETO DE EXTENSÃO.docx** - Especificação completa do professor
   - Requisitos obrigatórios
   - Critérios de avaliação
   - Estrutura da apresentação

2. **o que foi feito.docx** - Documentação da equipe anterior (Java Minds)
   - Módulos implementados
   - Dificuldades enfrentadas
   - Repositórios GitHub

3. **EVENTOS SENTINELAS(1).docx** - Check-list de plantão
   - 8 tipos de eventos a monitorar
   - Dados vitais diários
   - Formato de registro

4. **INDICADORES DE AVALIAÇÃO DAS INSTITUIÇÕES DE LONGA PERMANÊNCIA PARA IDOSOS(1) (1)(1).docx**
   - RDC/ANVISA Nº 502 DE 27 DE MAIO DE 2021
   - 6 indicadores obrigatórios
   - Fórmulas de cálculo
   - Dados históricos do Recanto do Sagrado Coração (2024)

### Links Úteis

- **Repositório Back-end:** https://github.com/Gustavotcsi/Projeto
- **Repositório Front-end:** https://github.com/pollymelo/front-java
- **Protótipo Figma:** https://www.figma.com/design/G9bJF5PT1qqwb1IkxEyaa1/Recanto-do-sagrado-coração

---

## 🔧 Comandos Úteis

### Maven

```cmd
# Compilar o projeto
mvnw.cmd clean compile

# Executar a aplicação
mvnw.cmd spring-boot:run

# Executar testes
mvnw.cmd test

# Gerar JAR executável
mvnw.cmd clean package

# Limpar build
mvnw.cmd clean

# Atualizar dependências
mvnw.cmd dependency:resolve

# Verificar dependências desatualizadas
mvnw.cmd versions:display-dependency-updates
```

### MySQL

```sql
-- Criar banco de dados
CREATE DATABASE projetoextensao;

-- Usar banco de dados
USE projetoextensao;

-- Listar tabelas
SHOW TABLES;

-- Ver estrutura de uma tabela
DESCRIBE pacientes;

-- Backup do banco
mysqldump -u root -p projetoextensao > backup.sql

-- Restaurar backup
mysql -u root -p projetoextensao < backup.sql
```

---

## 📝 Notas Importantes

### Configuração do Ambiente

- **Java 17** é obrigatório (não funciona com versões anteriores)
- **Lombok** - certifique-se de que sua IDE tem o plugin instalado
  - IntelliJ: File > Settings > Plugins > Buscar "Lombok"
  - Eclipse: Baixar lombok.jar e executar
- **MySQL** deve estar rodando na porta 3306
- Alterar senha do MySQL em `application.properties`

### Banco de Dados

- O banco é criado automaticamente pelo Hibernate (`ddl-auto=update`)
- **CUIDADO:** `ddl-auto=update` pode causar perda de dados em produção
- Considerar usar Flyway ou Liquibase para migrações
- Fazer backup antes de rodar o projeto pela primeira vez

### Segurança

- ⚠️ **CRÍTICO:** As senhas estão em texto plano no banco
- ⚠️ Não há controle de sessão
- ⚠️ Não há logs de auditoria
- **IMPLEMENTAR SPRING SECURITY URGENTEMENTE**

### Performance

- Não há paginação nas listagens (pode travar com muitos registros)
- Não há índices customizados no banco
- Considerar adicionar cache (Spring Cache)

### Arquitetura

- A aplicação é **desktop** (Swing) - não é web
- Não há API REST exposta (apenas uso interno)
- Não há controle de transações explícito - considerar adicionar `@Transactional`
- Usar `@Transactional` nos métodos de Service que fazem múltiplas operações

---

## 🎓 Estrutura da Apresentação (10/12/2025)

Conforme especificação do professor, a apresentação deve conter:

### 1. Contextualização
- Problema: Gestão manual e dispersa de dados em ILPI
- Solução: Sistema desktop integrado

### 2. Descrição do Problema
- Lista das funcionalidades implementadas
- Módulos desenvolvidos

### 3. Proposta de Solução/Implementação
- Arquitetura MVC
- Tecnologias: Java 17, Spring Boot, MySQL, Swing
- Diagramas de classes
- Estrutura de pacotes

### 4. Dificuldades e Limitações
- Desafios técnicos enfrentados
- Módulos não implementados (se houver)
- Melhorias futuras

### 5. Conclusão
- Resumo do que foi entregue
- Aderência aos objetivos
- Próximos passos

---

## 🤝 Contato e Suporte

### Professor
- **Nome:** Maurício Moreira Neto
- **Email:** mauricio.moreira@unichristus.edu.br
- **Instituição:** Unichristus - Centros Universitários Christus

### Equipe Anterior (2025-1)
- **Equipe:** Java Minds
- Consultar documento "o que foi feito.docx" para detalhes

### Cliente
- **Instituição:** Recanto do Sagrado Coração
- **Responsável Técnico:** Fabiana Ribeiro Sampaio Bündchen
- **Email:** ssocialrecanto@gmail.com
- **Telefone:** (85) 3281-4139

---

## 📌 Checklist de Entrega

### Código
- [ ] Código-fonte completo no GitHub
- [ ] README.md atualizado
- [ ] Arquivo .sql com estrutura do banco
- [ ] Script de dados iniciais (seed data)
- [ ] Arquivo .jar executável

### Documentação
- [ ] Diagrama de classes atualizado
- [ ] Documentação técnica
- [ ] Manual de instalação
- [ ] Manual de usuário (básico)

### Apresentação
- [ ] Slides conforme template
- [ ] Demonstração prática preparada
- [ ] Vídeo de apresentação (se solicitado)
- [ ] Todos os membros preparados para apresentar

### Testes
- [ ] Todos os módulos testados
- [ ] Casos de teste documentados
- [ ] Bugs conhecidos documentados

---

---

## 💡 Dicas Práticas de Implementação

### Para Começar Rapidamente

1. **Clone o repositório existente**
   ```cmd
   git clone https://github.com/Gustavotcsi/Projeto
   cd Projeto
   ```

2. **Configure o banco de dados**
   - Instale MySQL
   - Crie o banco: `CREATE DATABASE projetoextensao;`
   - Ajuste `application.properties`

3. **Execute o projeto**
   ```cmd
   mvnw.cmd spring-boot:run
   ```

4. **Teste os módulos existentes**
   - Login com admin/1234
   - Navegue pelo dashboard
   - Teste cadastro de pacientes
   - Teste agendamento de consultas

### Padrão de Desenvolvimento

**Para criar um novo módulo completo, siga esta ordem:**

1. **Model** (entidade JPA)
   ```java
   @Entity
   @Table(name = "nome_tabela")
   @Getter @Setter @NoArgsConstructor @AllArgsConstructor
   public class MinhaEntidade {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       // ... outros campos
   }
   ```

2. **Repository** (interface Spring Data)
   ```java
   public interface MeuRepositorio extends JpaRepository<MinhaEntidade, Long> {
       // Métodos de consulta customizados
   }
   ```

3. **Service** (lógica de negócio)
   ```java
   @Service
   public class MeuService {
       @Autowired
       private MeuRepositorio repositorio;
       
       public List<MinhaEntidade> listarTodos() {
           return repositorio.findAll();
       }
       // ... outros métodos
   }
   ```

4. **View** (tela Swing)
   ```java
   @Component
   @NoArgsConstructor
   public class MinhaTelaJFrame extends JFrame {
       @Autowired
       private MeuService service;
       
       @PostConstruct
       private void initUI() {
           // Configuração da tela
       }
   }
   ```

5. **Integrar no NavigationService**
   ```java
   public void abrirMinhaTela() {
       MinhaTelaJFrame tela = context.getBean(MinhaTelaJFrame.class);
       tela.setVisible(true);
   }
   ```

6. **Adicionar botão no Dashboard** (TelaGeral.java)

### Bibliotecas Úteis para Adicionar

**Para Relatórios PDF:**
```xml
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itext7-core</artifactId>
    <version>7.2.5</version>
</dependency>
```

**Para Exportar Excel:**
```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.3</version>
</dependency>
```

**Para Validação de CPF:**
```xml
<dependency>
    <groupId>br.com.caelum.stella</groupId>
    <artifactId>caelum-stella-core</artifactId>
    <version>2.1.5</version>
</dependency>
```

**Para Gráficos:**
```xml
<dependency>
    <groupId>org.jfree</groupId>
    <artifactId>jfreechart</artifactId>
    <version>1.5.4</version>
</dependency>
```

### Dicas de Swing

**Criar tabela com dados:**
```java
String[] colunas = {"ID", "Nome", "CPF"};
DefaultTableModel model = new DefaultTableModel(colunas, 0);
JTable tabela = new JTable(model);

// Adicionar dados
for (Paciente p : pacientes) {
    model.addRow(new Object[]{p.getId(), p.getNomeCompleto(), p.getCpf()});
}
```

**DatePicker (para campos de data):**
```xml
<dependency>
    <groupId>com.github.lgooddatepicker</groupId>
    <artifactId>LGoodDatePicker</artifactId>
    <version>11.2.1</version>
</dependency>
```

**Mensagens de confirmação:**
```java
int resposta = JOptionPane.showConfirmDialog(
    this,
    "Deseja realmente excluir?",
    "Confirmação",
    JOptionPane.YES_NO_OPTION
);
if (resposta == JOptionPane.YES_OPTION) {
    // Executar exclusão
}
```

### Tratamento de Erros

**Padrão para Services:**
```java
@Service
public class MeuService {
    public void salvar(MinhaEntidade entidade) {
        try {
            validar(entidade);
            repositorio.save(entidade);
        } catch (ValidationException e) {
            throw new BusinessException("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("Erro ao salvar: " + e.getMessage());
        }
    }
}
```

**Padrão para Views:**
```java
try {
    service.salvar(entidade);
    JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
    limparFormulario();
} catch (BusinessException e) {
    JOptionPane.showMessageDialog(
        this,
        e.getMessage(),
        "Erro",
        JOptionPane.ERROR_MESSAGE
    );
}
```

### Git - Boas Práticas

**Commits descritivos:**
```bash
git commit -m "feat: implementa módulo de eventos sentinelas"
git commit -m "fix: corrige validação de CPF"
git commit -m "docs: atualiza README com instruções de instalação"
```

**Branches por funcionalidade:**
```bash
git checkout -b feature/eventos-sentinelas
git checkout -b feature/prontuarios
git checkout -b fix/validacao-cpf
```

---

## 🚨 Problemas Comuns e Soluções

### Erro: "Port 8080 already in use"
**Solução:** Alterar porta em `application.properties`
```properties
server.port=8081
```

### Erro: "Access denied for user 'root'@'localhost'"
**Solução:** Verificar senha do MySQL em `application.properties`

### Erro: "Cannot resolve symbol 'Lombok'"
**Solução:** Instalar plugin Lombok na IDE e habilitar annotation processing

### Erro: "Table doesn't exist"
**Solução:** Verificar `spring.jpa.hibernate.ddl-auto=update` em `application.properties`

### Tela não abre / NullPointerException
**Solução:** Verificar se a classe tem `@Component` e se está sendo injetada corretamente

### Dados não aparecem na tabela
**Solução:** Verificar se o método `findAll()` está retornando dados e se o modelo da tabela está sendo atualizado

---

**Última atualização:** 21 de Novembro de 2025  
**Versão do Projeto:** 0.0.1-SNAPSHOT  
**Prazo de Entrega:** 10 de Dezembro de 2025  

---

**Boa sorte com o desenvolvimento! 🚀**
