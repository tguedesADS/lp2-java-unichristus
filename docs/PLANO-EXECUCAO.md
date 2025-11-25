# Plano de Execução - Projeto Recanto do Sagrado Coração

**Período:** 21/11/2025 a 10/12/2025 (19 dias úteis)  
**Data de Entrega:** 10/12/2025  
**Apresentação:** 10/12/2025

---

## 📅 Visão Geral do Cronograma

| Fase | Período | Duração | Status |
|------|---------|---------|--------|
| Fase 0: Instalação e Configuração | 21-22/11 | 2 dias | ⏳ Pendente |
| Fase 1: Consolidação | 23-25/11 | 3 dias | ⏳ Pendente |
| Fase 2: Módulos Críticos ANVISA | 26/11-02/12 | 7 dias | ⏳ Pendente |
| Fase 3: Módulos de Gestão | 03-06/12 | 4 dias | ⏳ Pendente |
| Fase 4: Módulos Complementares | 07/12 | 1 dia | ⏳ Pendente |
| Fase 5: Refinamento e Entrega | 08-10/12 | 3 dias | ⏳ Pendente |

---

## 🚀 FASE 0: INSTALAÇÃO E CONFIGURAÇÃO

**Período:** 21-22/11/2025 (2 dias)  
**Objetivo:** Preparar ambiente de desenvolvimento e validar projeto existente

### Task 0.1: Configuração do Ambiente de Desenvolvimento
**Prazo:** 21/11/2025 (manhã)  
**Responsável:** Todos os membros da equipe

- [ ] 0.1.1 Instalar Java JDK 17
  - Baixar de https://www.oracle.com/java/technologies/downloads/
  - Configurar JAVA_HOME
  - Verificar: `java -version`
  
- [ ] 0.1.2 Instalar MySQL 8.0+
  - Baixar MySQL Community Server
  - Configurar senha root
  - Iniciar serviço MySQL
  - Verificar: `mysql --version`


- [ ] 0.1.3 Instalar IDE (IntelliJ IDEA ou Eclipse)
  - Instalar plugin Lombok
  - Configurar JDK 17 na IDE
  - Habilitar annotation processing

- [ ] 0.1.4 Instalar Git
  - Configurar usuário: `git config --global user.name "Seu Nome"`
  - Configurar email: `git config --global user.email "seu@email.com"`

- [ ] 0.1.5 Instalar MySQL Workbench (opcional, mas recomendado)
  - Para visualizar e gerenciar banco de dados

### Task 0.2: Clonar e Configurar Projeto
**Prazo:** 21/11/2025 (tarde)  
**Responsável:** Tech Lead

- [ ] 0.2.1 Clonar repositório back-end
  ```bash
  git clone https://github.com/Gustavotcsi/Projeto
  cd Projeto
  ```

- [ ] 0.2.2 Criar banco de dados
  ```sql
  CREATE DATABASE projetoextensao;
  CREATE USER 'projeto_user'@'localhost' IDENTIFIED BY 'senha_segura';
  GRANT ALL PRIVILEGES ON projetoextensao.* TO 'projeto_user'@'localhost';
  FLUSH PRIVILEGES;
  ```

- [ ] 0.2.3 Configurar application.properties
  - Ajustar URL do banco
  - Ajustar usuário e senha
  - Verificar porta (8080)

- [ ] 0.2.4 Baixar dependências Maven
  ```bash
  mvnw.cmd clean install
  ```

### Task 0.3: Validar Projeto Existente
**Prazo:** 22/11/2025 (manhã)  
**Responsável:** Todos

- [ ] 0.3.1 Executar aplicação
  ```bash
  mvnw.cmd spring-boot:run
  ```

- [ ] 0.3.2 Testar login
  - Usuário: admin
  - Senha: 1234
  - Verificar se abre dashboard

- [ ] 0.3.3 Testar módulo de Pacientes
  - Cadastrar paciente teste
  - Listar pacientes
  - Editar paciente
  - Verificar persistência no banco

- [ ] 0.3.4 Testar módulo de Consultas
  - Agendar consulta teste
  - Listar consultas
  - Verificar relacionamento com paciente

- [ ] 0.3.5 Documentar bugs encontrados
  - Criar arquivo BUGS.md
  - Listar problemas identificados
  - Priorizar correções

### Task 0.4: Configurar Repositório da Equipe
**Prazo:** 22/11/2025 (tarde)  
**Responsável:** Tech Lead

- [ ] 0.4.1 Criar fork ou novo repositório
  - Criar repositório no GitHub da equipe
  - Adicionar todos os membros como colaboradores

- [ ] 0.4.2 Configurar branches
  - main (produção)
  - develop (desenvolvimento)
  - feature/* (funcionalidades)

- [ ] 0.4.3 Configurar .gitignore
  - Ignorar target/
  - Ignorar .idea/ ou .settings/
  - Ignorar application-local.properties

- [ ] 0.4.4 Fazer commit inicial
  ```bash
  git add .
  git commit -m "chore: setup inicial do projeto"
  git push origin main
  ```

---

## 🔧 FASE 1: CONSOLIDAÇÃO E PREPARAÇÃO

**Período:** 23-25/11/2025 (3 dias)  
**Objetivo:** Corrigir bugs, implementar validações e melhorar segurança

### Task 1.1: Correção de Bugs Identificados
**Prazo:** 23/11/2025  
**Responsável:** Desenvolvedor Back-end

- [ ] 1.1.1 Corrigir bugs críticos
  - Revisar lista de bugs do Task 0.3.5
  - Corrigir problemas de persistência
  - Corrigir problemas de navegação entre telas

- [ ] 1.1.2 Testar correções
  - Executar testes manuais
  - Validar com equipe

- [ ] 1.1.3 Commit das correções
  ```bash
  git commit -m "fix: corrige bugs identificados na validação inicial"
  ```

### Task 1.2: Implementar Validações Básicas
**Prazo:** 24/11/2025  
**Responsável:** Desenvolvedor Back-end

- [ ] 1.2.1 Adicionar dependência de validação CPF
  ```xml
  <dependency>
      <groupId>br.com.caelum.stella</groupId>
      <artifactId>caelum-stella-core</artifactId>
      <version>2.1.5</version>
  </dependency>
  ```

- [ ] 1.2.2 Criar classe ValidadorUtil
  - Método validarCPF(String cpf)
  - Método validarCartaoSUS(String cartaoSUS)
  - Método validarDataNascimento(LocalDate data)

- [ ] 1.2.3 Aplicar validações no PacienteService
  - Validar antes de salvar
  - Lançar exceções customizadas

- [ ] 1.2.4 Atualizar telas para exibir erros de validação
  - TelaCadastroPacientes
  - Exibir mensagens claras ao usuário

- [ ] 1.2.5 Testar validações
  - Tentar cadastrar CPF inválido
  - Tentar cadastrar data futura
  - Verificar mensagens de erro

### Task 1.3: Implementar Segurança Básica
**Prazo:** 25/11/2025  
**Responsável:** Desenvolvedor Back-end

- [ ] 1.3.1 Adicionar dependência Spring Security
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  ```

- [ ] 1.3.2 Criar classe SecurityConfig
  - Configurar BCrypt para senhas
  - Desabilitar CSRF (aplicação desktop)
  - Configurar autenticação em memória (temporário)

- [ ] 1.3.3 Atualizar ResponsavelSaude
  - Adicionar campo perfil (ADMIN, ENFERMEIRO, MEDICO)
  - Criar enum Perfil

- [ ] 1.3.4 Implementar hash de senhas
  - Criar método para criptografar senha
  - Atualizar senhas existentes no banco

- [ ] 1.3.5 Atualizar TelaLogin
  - Integrar com Spring Security
  - Armazenar usuário logado em sessão

- [ ] 1.3.6 Testar autenticação
  - Login com senha correta
  - Login com senha incorreta
  - Verificar hash no banco

### Task 1.4: Preparar Estrutura para Novos Módulos
**Prazo:** 25/11/2025 (tarde)  
**Responsável:** Tech Lead

- [ ] 1.4.1 Criar pacotes vazios
  - models: EventoSentinela, DadosVitais, Vacina, Familiar, Documento
  - repositorios: para cada entidade
  - servicos: para cada entidade
  - view: para cada módulo

- [ ] 1.4.2 Criar interfaces base
  - BaseEntity (id, createdAt, updatedAt)
  - BaseService (métodos CRUD padrão)

- [ ] 1.4.3 Criar classes de exceção customizadas
  - BusinessException
  - ValidationException
  - NotFoundException

- [ ] 1.4.4 Commit da estrutura
  ```bash
  git commit -m "chore: prepara estrutura para novos módulos"
  ```

---

## ⚠️ FASE 2: MÓDULOS CRÍTICOS - COMPLIANCE ANVISA

**Período:** 26/11-02/12/2025 (7 dias)  
**Objetivo:** Implementar módulos obrigatórios para compliance regulatório

### Task 2.1: Módulo Eventos Sentinelas - Back-end
**Prazo:** 26-27/11/2025 (2 dias)  
**Responsável:** Desenvolvedor Back-end 1

- [ ] 2.1.1 Criar entidade EventoSentinela
  - Todos os campos conforme especificação
  - Enum TipoEvento (8 tipos)
  - Enum Gravidade
  - Relacionamentos com Paciente e ResponsavelSaude

- [ ] 2.1.2 Criar entidade DadosVitais
  - Campos: pressaoArterial, glicemia, temperatura
  - Relacionamento com Paciente

- [ ] 2.1.3 Criar repositórios
  - EventoSentinelaRepositorio
  - DadosVitaisRepositorio
  - Métodos de busca customizados

- [ ] 2.1.4 Criar services
  - EventoSentinelaService (CRUD + estatísticas)
  - DadosVitaisService (CRUD + histórico)
  - Métodos para cálculo de indicadores ANVISA

- [ ] 2.1.5 Testar persistência
  - Criar dados de teste
  - Verificar relacionamentos
  - Testar métodos de busca

### Task 2.2: Módulo Eventos Sentinelas - Front-end
**Prazo:** 28/11/2025 (1 dia)  
**Responsável:** Desenvolvedor Front-end 1

- [ ] 2.2.1 Criar TelaChecklistPlantao
  - Layout: tabela mensal (dias 1-31)
  - Linhas: tipos de eventos + dados vitais
  - Filtros: residente, mês/ano
  - Células clicáveis

- [ ] 2.2.2 Criar TelaRegistroEvento
  - Formulário completo
  - Campos obrigatórios marcados
  - Validações
  - Botões: Salvar, Cancelar

- [ ] 2.2.3 Criar TelaHistoricoEventos
  - Listagem cronológica
  - Filtros por tipo e período
  - Detalhes ao clicar

- [ ] 2.2.4 Integrar com NavigationService
  - Adicionar métodos de navegação
  - Conectar botão no dashboard

- [ ] 2.2.5 Testar fluxo completo
  - Registrar evento
  - Visualizar no check-list
  - Consultar histórico

### Task 2.3: Módulo Prontuários Médicos - Back-end
**Prazo:** 29/11/2025 (1 dia)  
**Responsável:** Desenvolvedor Back-end 2

- [ ] 2.3.1 Criar entidade ProntuarioMedico
  - Relacionamento OneToOne com Paciente
  - Relacionamentos com Consulta, Prescricao, Exame, Vacina

- [ ] 2.3.2 Criar entidade Prescricao
  - Campos: medicamento, dosagem, frequência, duração
  - Relacionamento com ProntuarioMedico

- [ ] 2.3.3 Criar entidade Exame
  - Campos: tipo, data, resultado, arquivo
  - Relacionamento com ProntuarioMedico

- [ ] 2.3.4 Atualizar entidade Consulta
  - Adicionar relacionamento com ProntuarioMedico
  - Adicionar campos: motivo, diagnóstico, anotações

- [ ] 2.3.5 Criar repositórios e services
  - ProntuarioMedicoService
  - PrescricaoService
  - ExameService
  - Métodos obrigatórios conforme especificação

- [ ] 2.3.6 Implementar métodos obrigatórios
  - adicionarConsulta()
  - vincularResultadoExame()
  - gerarResumoHistorico()
  - buscarConsultasPorData()
  - buscarConsultasPorProfissional()

### Task 2.4: Módulo Prontuários Médicos - Front-end
**Prazo:** 30/11/2025 (1 dia)  
**Responsável:** Desenvolvedor Front-end 2

- [ ] 2.4.1 Criar TelaProntuario
  - Header com dados do paciente
  - Abas: Consultas | Prescrições | Exames | Internações | Vacinas
  - Botão "Gerar Relatório Completo"

- [ ] 2.4.2 Criar TelaAdicionarPrescricao
  - Formulário de prescrição
  - Validações
  - Integração com service

- [ ] 2.4.3 Criar TelaAdicionarExame
  - Formulário de exame
  - Upload de arquivo (opcional)
  - Integração com service

- [ ] 2.4.4 Integrar com módulo de Consultas
  - Atualizar TelaConsultas para incluir novos campos
  - Vincular consultas ao prontuário

- [ ] 2.4.5 Testar fluxo completo
  - Abrir prontuário de paciente
  - Adicionar prescrição
  - Adicionar exame
  - Visualizar histórico

### Task 2.5: Módulo Relatórios ANVISA - Back-end
**Prazo:** 01/12/2025 (1 dia)  
**Responsável:** Desenvolvedor Back-end 1

- [ ] 2.5.1 Criar DTOs de relatórios
  - RelatorioIndicadoresAnvisaDTO
  - RelatorioIndividualDTO
  - RelatorioCoberturaVacinalDTO

- [ ] 2.5.2 Criar RelatorioService
  - Método calcularIndicadoresAnvisa(mes, ano)
  - Implementar 6 fórmulas ANVISA
  - Método gerarRelatorioIndividual(pacienteId)

- [ ] 2.5.3 Implementar cálculos dos indicadores
  - Taxa de mortalidade
  - Taxa de doença diarreica aguda
  - Taxa de escabiose
  - Taxa de desidratação
  - Taxa de úlcera de decúbito
  - Taxa de desnutrição

- [ ] 2.5.4 Testar cálculos
  - Criar dados de teste
  - Validar fórmulas
  - Comparar com planilha de referência

### Task 2.6: Módulo Relatórios ANVISA - Front-end
**Prazo:** 02/12/2025 (1 dia)  
**Responsável:** Desenvolvedor Front-end 1

- [ ] 2.6.1 Adicionar dependência JFreeChart
  ```xml
  <dependency>
      <groupId>org.jfree</groupId>
      <artifactId>jfreechart</artifactId>
      <version>1.5.4</version>
  </dependency>
  ```

- [ ] 2.6.2 Criar TelaIndicadoresAnvisa
  - Filtros: mês/ano
  - Tabela com 6 indicadores
  - Gráfico de linha (evolução mensal)
  - Botão "Exportar Excel"

- [ ] 2.6.3 Criar TelaRelatorioIndividual
  - Seleção de residente
  - Exibição de dados pessoais
  - Medicamentos em uso
  - Vacinas aplicadas
  - Eventos sentinelas
  - Botão "Exportar PDF"

- [ ] 2.6.4 Implementar exportação básica
  - Exportar para CSV (temporário)
  - Preparar estrutura para PDF/Excel

- [ ] 2.6.5 Testar relatórios
  - Gerar relatório de indicadores
  - Gerar relatório individual
  - Validar dados exibidos

---

## 👥 FASE 3: MÓDULOS DE GESTÃO

**Período:** 03-06/12/2025 (4 dias)  
**Objetivo:** Implementar módulos de gestão de familiares e vacinas

### Task 3.1: Módulo Gestão de Familiares - Back-end
**Prazo:** 03/12/2025 (manhã)  
**Responsável:** Desenvolvedor Back-end 2

- [ ] 3.1.1 Criar/Finalizar entidade Familiar
  - Campos conforme especificação
  - Enum Parentesco
  - Relacionamento com Paciente

- [ ] 3.1.2 Criar FamiliarRepositorio
  - Métodos de busca por paciente
  - Buscar contatos de emergência

- [ ] 3.1.3 Criar FamiliarService
  - CRUD completo
  - Validações (CPF, telefone)
  - Regra: pelo menos 1 contato de emergência

- [ ] 3.1.4 Testar persistência

### Task 3.2: Módulo Gestão de Familiares - Front-end
**Prazo:** 03/12/2025 (tarde)  
**Responsável:** Desenvolvedor Front-end 2

- [ ] 3.2.1 Criar TelaFamiliares
  - Listagem por residente
  - Indicação visual de contato de emergência
  - Botões: Adicionar, Editar, Remover

- [ ] 3.2.2 Criar TelaCadastroFamiliar
  - Formulário completo
  - Checkboxes: Contato Emergência, Responsável Legal
  - Validações

- [ ] 3.2.3 Integrar com NavigationService

- [ ] 3.2.4 Testar fluxo completo

### Task 3.3: Módulo Controle de Vacinas - Back-end
**Prazo:** 04/12/2025 (manhã)  
**Responsável:** Desenvolvedor Back-end 1

- [ ] 3.3.1 Criar/Finalizar entidade Vacina
  - Campos conforme especificação
  - Relacionamento com Paciente e ProntuarioMedico

- [ ] 3.3.2 Criar VacinaRepositorio
  - Buscar por paciente
  - Buscar vacinas pendentes

- [ ] 3.3.3 Criar VacinaService
  - CRUD completo
  - Cálculo de próxima dose
  - Alertas de vacinas atrasadas
  - Cálculo de cobertura vacinal

- [ ] 3.3.4 Testar persistência

### Task 3.4: Módulo Controle de Vacinas - Front-end
**Prazo:** 04/12/2025 (tarde)  
**Responsável:** Desenvolvedor Front-end 1

- [ ] 3.4.1 Criar TelaCartaoVacinal
  - Seleção de residente
  - Tabela com histórico
  - Alertas visuais (pendente/atrasada)
  - Botão "Registrar Nova Dose"

- [ ] 3.4.2 Criar TelaRegistroVacina
  - Formulário completo
  - Cálculo automático de próxima dose
  - Validações

- [ ] 3.4.3 Criar TelaCalendarioVacinal
  - Visão geral de todas as residentes
  - Status de cada vacina (✓ ⚠ ✗)

- [ ] 3.4.4 Integrar com NavigationService

- [ ] 3.4.5 Testar fluxo completo

### Task 3.5: Relatórios Gerenciais
**Prazo:** 05-06/12/2025 (2 dias)  
**Responsável:** Desenvolvedor Back-end 2 + Front-end 2

- [ ] 3.5.1 Implementar RelatorioCoberturaVacinal (back-end)
  - Cálculo de percentual
  - Lista de pendentes

- [ ] 3.5.2 Criar TelaCoberturaVacinal (front-end)
  - Seleção de vacina
  - Gráfico de pizza
  - Lista de pendentes

- [ ] 3.5.3 Adicionar exportação PDF
  - Adicionar dependência iText7
  - Implementar geração de PDF para relatórios

- [ ] 3.5.4 Adicionar exportação Excel
  - Adicionar dependência Apache POI
  - Implementar geração de Excel para indicadores

- [ ] 3.5.5 Testar exportações

---

## 📄 FASE 4: MÓDULOS COMPLEMENTARES

**Período:** 07/12/2025 (1 dia)  
**Objetivo:** Implementar módulo de documentos (se houver tempo)

### Task 4.1: Módulo Documentos (OPCIONAL)
**Prazo:** 07/12/2025  
**Responsável:** Desenvolvedor disponível

- [ ] 4.1.1 Criar entidade Documento (back-end)
  - Campos conforme especificação
  - Enum TipoDocumento

- [ ] 4.1.2 Criar DocumentoRepositorio e Service

- [ ] 4.1.3 Implementar upload de arquivos
  - Armazenamento local
  - Validação de tipo e tamanho

- [ ] 4.1.4 Criar TelaDocumentos (front-end)
  - Upload com JFileChooser
  - Listagem
  - Visualização básica

- [ ] 4.1.5 Testar upload e download

**NOTA:** Se não houver tempo, este módulo pode ser deixado para depois da entrega.

---

## ✨ FASE 5: REFINAMENTO E ENTREGA

**Período:** 08-10/12/2025 (3 dias)  
**Objetivo:** Testes, correções, documentação e preparação da apresentação

### Task 5.1: Testes Integrados
**Prazo:** 08/12/2025  
**Responsável:** Todos

- [ ] 5.1.1 Testar todos os módulos
  - Login e autenticação
  - Pacientes (CRUD completo)
  - Consultas (CRUD completo)
  - Eventos Sentinelas (registro e check-list)
  - Prontuários (visualização e adição)
  - Familiares (CRUD completo)
  - Vacinas (registro e cartão)
  - Relatórios (geração e exportação)

- [ ] 5.1.2 Testar fluxos integrados
  - Cadastrar paciente → Agendar consulta → Registrar no prontuário
  - Registrar evento → Visualizar em relatório ANVISA
  - Registrar vacina → Visualizar em prontuário e relatório

- [ ] 5.1.3 Testar validações
  - CPF inválido
  - Campos obrigatórios
  - Datas inválidas

- [ ] 5.1.4 Documentar bugs encontrados
  - Criar issues no GitHub
  - Priorizar correções

### Task 5.2: Correções Finais
**Prazo:** 08/12/2025 (tarde)  
**Responsável:** Desenvolvedores

- [ ] 5.2.1 Corrigir bugs críticos
  - Bugs que impedem uso do sistema

- [ ] 5.2.2 Corrigir bugs importantes
  - Bugs que afetam experiência do usuário

- [ ] 5.2.3 Melhorias de interface
  - Padronizar espaçamentos
  - Corrigir alinhamentos
  - Adicionar tooltips

- [ ] 5.2.4 Commit das correções

### Task 5.3: Melhorias de Interface
**Prazo:** 09/12/2025 (manhã)  
**Responsável:** Desenvolvedores Front-end

- [ ] 5.3.1 Padronizar todas as telas
  - Mesmas cores
  - Mesmas fontes
  - Mesmos espaçamentos

- [ ] 5.3.2 Adicionar feedback visual
  - Loading ao salvar
  - Mensagens de sucesso
  - Confirmações de exclusão

- [ ] 5.3.3 Melhorar usabilidade
  - Atalhos de teclado (Enter para salvar)
  - Tab order correto
  - Focus inicial em campo correto

- [ ] 5.3.4 Testar em diferentes resoluções

### Task 5.4: Documentação
**Prazo:** 09/12/2025 (tarde)  
**Responsável:** Tech Lead + Project Manager

- [ ] 5.4.1 Atualizar README.md
  - Instruções de instalação
  - Instruções de execução
  - Credenciais padrão
  - Tecnologias utilizadas

- [ ] 5.4.2 Criar MANUAL-USUARIO.md
  - Como usar cada módulo
  - Prints de tela
  - Fluxos principais

- [ ] 5.4.3 Atualizar diagrama de classes
  - Incluir novas entidades
  - Atualizar relacionamentos

- [ ] 5.4.4 Criar script SQL de dados iniciais
  - Usuário admin
  - Dados de exemplo
  - Tipos de consulta, vacinas, etc.

- [ ] 5.4.5 Documentar código
  - Adicionar JavaDoc em classes principais
  - Comentar lógicas complexas

### Task 5.5: Preparação da Apresentação
**Prazo:** 10/12/2025 (manhã)  
**Responsável:** Todos

- [ ] 5.5.1 Criar slides da apresentação
  - Seguir template do professor
  - Contextualização
  - Descrição do problema
  - Proposta de solução
  - Arquitetura e tecnologias
  - Dificuldades e limitações
  - Conclusão

- [ ] 5.5.2 Preparar demonstração prática
  - Definir fluxo de demonstração
  - Preparar dados de exemplo
  - Testar demonstração

- [ ] 5.5.3 Gravar vídeo de apresentação (se necessário)
  - Demonstração de cada módulo
  - Narração explicativa

- [ ] 5.5.4 Dividir falas entre membros
  - Cada membro apresenta parte que desenvolveu

- [ ] 5.5.5 Ensaiar apresentação
  - Cronometrar tempo
  - Ajustar conforme necessário

### Task 5.6: Entrega Final
**Prazo:** 10/12/2025 (tarde)  
**Responsável:** Tech Lead

- [ ] 5.6.1 Gerar JAR executável
  ```bash
  mvnw.cmd clean package
  ```

- [ ] 5.6.2 Testar JAR
  - Executar em máquina limpa
  - Verificar se funciona

- [ ] 5.6.3 Preparar pacote de entrega
  - Código-fonte (ZIP do repositório)
  - JAR executável
  - Script SQL
  - Documentação (README, MANUAL)
  - Diagrama de classes
  - Slides da apresentação

- [ ] 5.6.4 Fazer commit final
  ```bash
  git tag v1.0.0
  git push origin v1.0.0
  ```

- [ ] 5.6.5 Enviar para professor
  - Conforme instruções fornecidas

---

## 📊 Distribuição de Responsabilidades Sugerida

### Desenvolvedor Back-end 1
- Eventos Sentinelas (back-end)
- Relatórios ANVISA (back-end)
- Vacinas (back-end)
- Validações e segurança

### Desenvolvedor Back-end 2
- Prontuários (back-end)
- Familiares (back-end)
- Prescrições e Exames
- Integração entre módulos

### Desenvolvedor Front-end 1
- Eventos Sentinelas (front-end)
- Relatórios ANVISA (front-end)
- Vacinas (front-end)
- Gráficos e visualizações

### Desenvolvedor Front-end 2
- Prontuários (front-end)
- Familiares (front-end)
- Melhorias de interface
- Padronização visual

### Tech Lead
- Arquitetura e estrutura
- Revisão de código
- Integração entre módulos
- Resolução de conflitos

### Project Manager
- Acompanhamento de prazos
- Documentação
- Preparação da apresentação
- Comunicação com professor

---

## ⚠️ Alertas e Observações Importantes

### Prioridades Absolutas (NÃO PODEM FALTAR)
1. ✅ Eventos Sentinelas (ANVISA)
2. ✅ Prontuários Médicos (requisito professor)
3. ✅ Relatórios ANVISA (compliance)

### Se Houver Atraso
- Deixar módulo Documentos para depois
- Simplificar exportação de relatórios (apenas CSV)
- Focar em funcionalidades core

### Reuniões Diárias Sugeridas
- **Horário:** 19h (início da aula)
- **Duração:** 15 minutos
- **Pauta:**
  - O que fiz ontem?
  - O que farei hoje?
  - Tenho algum impedimento?

### Commits e Branches
- Fazer commits pequenos e frequentes
- Usar branches feature/* para cada funcionalidade
- Fazer merge para develop após revisão
- Merge para main apenas código testado

### Testes
- Testar SEMPRE antes de fazer commit
- Não commitar código que não compila
- Não commitar código que quebra funcionalidades existentes

---

## 📞 Contatos de Emergência

**Professor:** Maurício Moreira Neto  
**Email:** mauricio.moreira@unichristus.edu.br

**Cliente:** Recanto do Sagrado Coração  
**Email:** ssocialrecanto@gmail.com  
**Telefone:** (85) 3281-4139

---

**Última atualização:** 21 de Novembro de 2025  
**Próxima revisão:** Diária durante o desenvolvimento

**BOA SORTE! 🚀**
