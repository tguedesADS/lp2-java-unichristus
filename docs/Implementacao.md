# Refatorações

#### Alterações realizadas:

1. **Desabilitada a edição direta na tabela**
   - Modificado o `DefaultTableModel` para sobrescrever o método `isCellEditable()` retornando `false`
   - Isso impede que o usuário digite diretamente nas células da tabela

2. **Alterado o nome do botão**
   - Botão "Adicionar novo" renomeado para "Adicionar paciente"
   - Ajustado o tamanho do botão de 160px para 180px para acomodar o novo texto

3. **Implementada atualização automática da tabela**
   - Criado método `atualizarTabela()` na classe `TelaPacientes`
   - Este método busca todos os pacientes do banco de dados e atualiza a tabela
   - Adicionada injeção de dependência de `TelaPacientes` na classe `TelaCadastroPacientes`
   - Após salvar um novo paciente, a tabela é automaticamente atualizada
   - Os campos do formulário são limpos após o salvamento

4. **Adicionado scroll na tabela**
   - Configurado `JScrollPane` com scroll vertical sempre visível
   - Scroll horizontal aparece quando necessário
   - Definido tamanho preferencial de 800x400 pixels para a área de scroll

#### Arquivos modificados:
- `TelaPacientes.java` - Classe principal da tela de consulta de pacientes
- `TelaCadastroPacientes.java` - Classe da tela de cadastro de pacientes

#### Como funciona:
- A tabela agora é somente leitura, o usuário não pode editar os dados diretamente
- Quando o usuário clica em "Adicionar paciente", abre a tela de cadastro
- Após preencher os dados e clicar em "Salvar", o paciente é salvo no banco de dados
- A tabela da tela de pacientes é automaticamente atualizada com o novo registro
- Os campos do formulário são limpos, permitindo cadastrar outro paciente rapidamente


---

## Data: 27/11/2025 - Parte 2

### Validações e Formatações no Cadastro de Pacientes

#### Alterações realizadas:

1. **Formatação automática do CPF**
   - Implementado campo formatado com máscara `###.###.###-##`
   - O CPF é exibido no formato XXX.XXX.XXX-XX enquanto o usuário digita
   - Ao salvar, a formatação é removida e apenas os 11 dígitos são armazenados
   - Validação: CPF deve ter exatamente 11 dígitos numéricos

2. **Formatação de Data de Nascimento**
   - Campo convertido para `JFormattedTextField` com máscara `##/##/####`
   - Formato obrigatório: DD/MM/AAAA
   - Validação: campo não pode estar vazio ou incompleto
   - Conversão automática para `LocalDate` ao salvar

3. **Formatação de Data de Entrada**
   - Campo convertido para `JFormattedTextField` com máscara `##/##/####`
   - Formato obrigatório: DD/MM/AAAA
   - Validação: campo não pode estar vazio ou incompleto
   - Conversão automática para `LocalDate` ao salvar

4. **Validação do Cartão SUS**
   - Campo validado para aceitar apenas números
   - Validação: campo obrigatório e deve conter apenas dígitos numéricos

5. **Limpeza automática do formulário**
   - Ao clicar em "Adicionar paciente", o formulário é limpo automaticamente
   - Isso evita que dados antigos permaneçam nos campos
   - Implementado método `limparCamposAoAbrir()` chamado pelo `NavigationService`

6. **Validações de campos obrigatórios**
   - Nome completo: não pode estar vazio
   - CPF: deve ter 11 dígitos
   - Data de nascimento: campo obrigatório
   - Cartão SUS: campo obrigatório e numérico
   - Data de entrada: campo obrigatório
   - Mensagens de erro são exibidas ao usuário quando alguma validação falha

#### Arquivos modificados:
- `TelaCadastroPacientes.java` - Adicionadas validações e formatações
- `NavigationService.java` - Modificado para limpar formulário ao abrir

#### Métodos criados:
- `createFormattedTextField()` - Cria campos com máscara de formatação
- `validarCampos()` - Valida todos os campos obrigatórios antes de salvar
- `converterData()` - Converte data de DD/MM/AAAA para LocalDate
- `limparCamposAoAbrir()` - Limpa os campos ao abrir a tela de cadastro

#### Como funciona:
- Os campos CPF, Data de Nascimento e Data de Entrada agora têm máscaras de formatação
- O usuário digita e a formatação é aplicada automaticamente
- Ao tentar salvar, todas as validações são executadas
- Se alguma validação falhar, uma mensagem de erro é exibida
- Apenas após todas as validações passarem, o paciente é salvo no banco
- Ao clicar em "Adicionar paciente", o formulário sempre abre limpo


---

## Data: 27/11/2025 - Parte 3

### Implementação de Edição de Pacientes e Status Ativo/Inativo

#### Alterações realizadas:

1. **Adicionado campo 'ativo' no modelo Paciente**
   - Novo campo booleano `ativo` na entidade Paciente
   - Por padrão, todo paciente novo é cadastrado como ativo (ativo = true)
   - Campo obrigatório no banco de dados

2. **Atualização do banco de dados**
   - Adicionada coluna `ativo` na tabela `pacientes`
   - Script SQL atualizado no arquivo `init.sql`
   - Valor padrão: TRUE (paciente ativo)

3. **Nova coluna "Situação" na tabela**
   - Adicionada coluna que exibe "Ativo" ou "Inativo"
   - Coluna ID adicionada (oculta) para identificar o paciente ao editar
   - A tabela agora mostra o status de cada paciente

4. **Funcionalidade de edição de pacientes**
   - Duplo clique em uma linha da tabela abre o formulário de edição
   - O formulário é preenchido automaticamente com os dados do paciente
   - Título da janela muda para "Editar Paciente"
   - Mesmo formulário usado para cadastro e edição

5. **Checkbox "Paciente Inativo"**
   - Adicionado checkbox no formulário de cadastro/edição
   - Quando marcado, o paciente fica inativo
   - Quando desmarcado, o paciente fica ativo
   - Aparece apenas no modo de edição ou pode ser usado no cadastro

6. **Filtros por status funcionando**
   - Filtro "Todos": exibe todos os pacientes (ativos e inativos)
   - Filtro "Ativos": exibe apenas pacientes ativos
   - Filtro "Inativos": exibe apenas pacientes inativos
   - Os filtros funcionam em conjunto com busca por nome e CPF

7. **Métodos adicionados no repositório e service**
   - `findByAtivo(Boolean ativo)` - busca pacientes por status
   - `findPacienteById(Long id)` - busca paciente por ID
   - `atualizarPaciente(Paciente paciente)` - atualiza dados do paciente

#### Arquivos modificados:
- `Paciente.java` - Adicionado campo `ativo`
- `PacienteRepositorio.java` - Adicionado método de busca por status
- `PacienteService.java` - Adicionados métodos de busca e atualização
- `TelaPacientes.java` - Adicionada coluna situação, filtros e duplo clique
- `TelaCadastroPacientes.java` - Adicionado checkbox e modo de edição
- `NavigationService.java` - Adicionado método para abrir tela de edição
- `init.sql` - Adicionada coluna `ativo` na tabela pacientes

#### Como funciona:

**Cadastro:**
- Ao cadastrar um novo paciente, ele é automaticamente marcado como ativo
- O checkbox "Paciente Inativo" fica desmarcado por padrão

**Edição:**
- Dê duplo clique em qualquer linha da tabela de pacientes
- O formulário abre com os dados preenchidos
- Marque o checkbox "Paciente Inativo" para desativar o paciente
- Clique em "Salvar" para atualizar os dados

**Filtros:**
- Selecione "Ativos", "Inativos" ou "Todos"
- Clique em "Pesquisar" para aplicar o filtro
- Os filtros funcionam com ou sem busca por nome/CPF

**Visualização:**
- A coluna "Situação" mostra se o paciente está "Ativo" ou "Inativo"
- Pacientes inativos podem ser reativados editando e desmarcando o checkbox


---

## Data: 27/11/2025 - Parte 4

### Melhorias na Experiência do Usuário ao Salvar/Editar Paciente

#### Alterações realizadas:

1. **Mensagens amigáveis de sucesso**
   - Mensagem para novo cadastro: "✓ Paciente cadastrado com sucesso! O paciente foi adicionado ao sistema."
   - Mensagem para edição: "✓ Paciente atualizado com sucesso! Os dados foram salvos no sistema."
   - Ícone de informação (INFORMATION_MESSAGE) para melhor visualização

2. **Retorno automático para tela anterior**
   - Após clicar em "OK" na mensagem de sucesso, a tela de cadastro fecha automaticamente
   - O usuário retorna para a tela de consulta de pacientes
   - A tabela já está atualizada com os novos dados

#### Arquivos modificados:
- `TelaCadastroPacientes.java` - Melhoradas as mensagens e adicionado fechamento automático

#### Como funciona:
- Ao salvar ou editar um paciente com sucesso, uma mensagem amigável é exibida
- Após clicar em "OK", a janela fecha automaticamente
- O usuário volta para a tela de consulta de pacientes
- A tabela já mostra os dados atualizados


---

## Data: 27/11/2025

### Adicionado Scroll na Tela de Consultas

#### Alterações realizadas:

1. **Implementado scroll vertical e horizontal**
   - Adicionado `JScrollPane` envolvendo o painel de detalhes da consulta
   - Scroll vertical sempre visível para facilitar navegação
   - Scroll horizontal aparece quando necessário
   - Definido tamanho preferencial de 800x400 pixels para a área de scroll

2. **Melhor visualização de conteúdo extenso**
   - Campos de texto grandes (Triagem e Anotações do Médico) agora são totalmente acessíveis
   - Usuário pode rolar para ver todo o conteúdo sem redimensionar a janela
   - Mantida a arquitetura e estrutura visual da tela

#### Arquivos modificados:
- `TelaConsultas.java` - Adicionado JScrollPane ao painel de detalhes

#### Como funciona:
- O painel "Detalhes da Consulta" agora possui scroll vertical sempre visível
- Quando o conteúdo é maior que a área visível, o usuário pode rolar para baixo
- O scroll horizontal aparece automaticamente se necessário
- A experiência de navegação é mais fluida e intuitiva


---

## Data: 30/11/2025 - Parte 1

### Formatação Automática do Campo CPF na Tela de Consultas

#### Alterações realizadas:

1. **Formatação automática do CPF enquanto digita**
   - Campo CPF aceita apenas números (0-9)
   - Formatação aplicada automaticamente no padrão XXX.XXX.XXX-XX
   - Limite de 11 dígitos numéricos
   - Pontos e traços são inseridos automaticamente nas posições corretas

2. **Validação de entrada**
   - Bloqueia caracteres não numéricos
   - Impede digitação além de 11 dígitos
   - Mantém a formatação ao editar ou apagar caracteres

3. **Validações completas na pesquisa**
   - Valida se o campo está vazio antes de pesquisar
   - Verifica se o CPF tem exatamente 11 dígitos
   - Valida se não é o texto do placeholder
   - Formatação é removida automaticamente antes de buscar no banco

4. **Tratamento de erros robusto**
   - Captura exceções quando paciente não existe no banco
   - Limpa campos de detalhes antes de exibir mensagens de erro
   - Mensagens específicas para cada tipo de erro:
     - Campo vazio: "Por favor, digite um CPF para pesquisar."
     - CPF incompleto: "CPF inválido. Digite um CPF com 11 dígitos."
     - Paciente não encontrado: "Não existe paciente vinculado ao CPF digitado."
     - Sem consultas: "Nenhuma consulta encontrada para este paciente."

#### Arquivos modificados:
- `TelaConsultas.java` - Adicionada formatação automática e melhorada a lógica de pesquisa

#### Métodos criados:
- `aplicarFormatacaoCPF(JTextField textField)` - Aplica formatação automática ao campo CPF usando DocumentFilter

#### Como funciona:
1. O usuário começa a digitar apenas números no campo "CPF do Paciente"
2. A formatação é aplicada automaticamente:
   - Após 3 dígitos: adiciona ponto (123.)
   - Após 6 dígitos: adiciona ponto (123.456.)
   - Após 9 dígitos: adiciona traço (123.456.789-)
   - Resultado final: 123.456.789-00
3. Ao clicar em "Pesquisar", o sistema valida o CPF
4. Remove a formatação e busca no banco de dados
5. Se encontrar, exibe os detalhes da consulta
6. Se não encontrar, limpa os campos e exibe mensagem apropriada

5. **Limpeza automática ao abrir a tela**
   - Campo de pesquisa é limpo automaticamente quando a tela é aberta
   - Placeholder é restaurado
   - Campos de detalhes da consulta são limpos
   - Garante que a tela sempre inicie em estado limpo

#### Exemplo de uso:
- Digite: "12345678900" (apenas números)
- Campo exibe automaticamente: "123.456.789-00"
- Clique em "Pesquisar"
- Sistema valida, busca e exibe os dados da consulta
- Se não encontrar, exibe mensagem de aviso clara
- Ao sair e retornar para a tela, o campo estará limpo


---

## Data: 30/11/2025 - Parte 2

### Melhorias Completas na Tela de Agendamento de Consulta

#### Alterações realizadas:

1. **Limpeza automática do formulário**
   - Campos são limpos automaticamente ao abrir a tela
   - Implementado `ComponentListener` que detecta quando a tela fica visível
   - Método `limparCampos()` restaura todos os campos ao estado inicial

2. **Formatação automática do CPF**
   - Campo CPF aceita apenas números (0-9)
   - Formatação aplicada automaticamente no padrão XXX.XXX.XXX-XX
   - Limite de 11 dígitos numéricos

3. **Formatação automática da Data**
   - Campo Data aceita apenas números (0-9)
   - Formatação aplicada automaticamente no padrão DD/MM/AAAA
   - Limite de 8 dígitos numéricos

4. **Horário com seleção de hora e minuto**
   - Substituído campo de texto livre por ComboBoxes
   - ComboBox de horas: 00 a 23
   - ComboBox de minutos: 00, 05, 10, 15... (intervalos de 5 minutos)
   - Interface mais intuitiva e sem erros de digitação

5. **Validação de CPF cadastrado**
   - Sistema verifica se o CPF existe no banco antes de agendar
   - Mensagem clara: "CPF não cadastrado! Por favor, cadastre o paciente primeiro."
   - Evita agendamentos para pacientes inexistentes

6. **Novos campos de texto adicionados**
   - **Motivo da Consulta** (obrigatório): Campo de texto com 3 linhas
   - **Diagnóstico** (opcional): Campo de texto com 3 linhas
   - **Anotações do Médico** (opcional): Campo de texto com 3 linhas
   - Todos com scroll e quebra de linha automática

7. **Validações completas de campos obrigatórios**
   - CPF: obrigatório, 11 dígitos
   - Médico: obrigatório, deve selecionar da lista
   - Tipo de Consulta: obrigatório, deve selecionar da lista
   - Data: obrigatória, formato DD/MM/AAAA
   - Motivo da Consulta: obrigatório
   - Diagnóstico e Anotações do Médico: opcionais

8. **Atualização do banco de dados**
   - Adicionadas colunas na tabela `consultas`:
     - `motivo_consulta` (TEXT)
     - `diagnostico` (TEXT)
     - `anotacoes_medico` (TEXT)

9. **Layout otimizado**
   - Campos reorganizados para caber tudo sem scroll
   - Campos de texto menores (3 linhas cada)
   - Espaçamento ajustado para melhor visualização

#### Arquivos modificados:
- `TelaAgendamentoConsulta.java` - Reformulação completa com novos campos e validações
- `Consulta.java` - Adicionados campos motivoConsulta, diagnostico e anotacoesMedico
- `ConsultaService.java` - Atualizado método salvarConsulta para aceitar novos parâmetros

#### Métodos criados:
- `limparCampos()` - Limpa todos os campos do formulário
- `validarCampos()` - Valida todos os campos obrigatórios
- `aplicarFormatacaoCPF(JTextField)` - Aplica formatação automática no CPF
- `aplicarFormatacaoData(JTextField)` - Aplica formatação automática na data

#### Como funciona:

**Ao abrir a tela:**
- Todos os campos são limpos automaticamente
- Formulário pronto para novo agendamento

**Preenchimento:**
- CPF: Digite apenas números, formatação automática (123.456.789-00)
- Data: Digite apenas números, formatação automática (25/11/2025)
- Horário: Selecione hora e minuto nos ComboBoxes
- Médico e Tipo de Consulta: Selecione nas listas
- Motivo da Consulta: Campo obrigatório, descreva o motivo
- Diagnóstico e Anotações: Campos opcionais

**Ao clicar em Agendar:**
1. Sistema valida todos os campos obrigatórios
2. Verifica se o CPF está cadastrado no sistema
3. Se tudo estiver correto, salva a consulta
4. Exibe mensagem de sucesso
5. Limpa o formulário e fecha a tela

**Mensagens de erro:**
- CPF inválido ou não cadastrado
- Campos obrigatórios não preenchidos
- Seleções não realizadas (médico, tipo de consulta)


---

## Data: 30/11/2025 - Parte 3

### Suporte a Múltiplas Consultas por Paciente

#### Alterações realizadas:

1. **Novo método no repositório**
   - `findAllByPaciente(Paciente paciente)` - Busca todas as consultas de um paciente

2. **Novos métodos no service**
   - `findAllConsultasByPaciente(Paciente paciente)` - Retorna lista de consultas
   - `findConsultaById(Long id)` - Busca consulta por ID

3. **Lógica inteligente de exibição**
   - Se o paciente tem 1 consulta: exibe direto os detalhes
   - Se o paciente tem múltiplas consultas: mostra lista para seleção
   - Se o paciente não tem consultas: exibe mensagem informativa

4. **Dialog de seleção de consultas**
   - Lista todas as consultas do paciente
   - Mostra: ID, Médico, Tipo, Data e Hora
   - Usuário seleciona qual consulta quer ver os detalhes

5. **Mensagens de erro melhoradas**
   - Diferencia paciente não encontrado de paciente sem consultas
   - Mostra CPF pesquisado para facilitar debug
   - Mostra nome do paciente quando encontrado

#### Arquivos modificados:
- `ConsultaRepositorio.java` - Adicionado método findAllByPaciente
- `ConsultaService.java` - Adicionados métodos para buscar múltiplas consultas
- `TelaConsultas.java` - Implementada lógica de seleção de consultas

#### Métodos criados:
- `mostrarListaConsultas(List<Consulta>, Paciente)` - Exibe dialog com lista de consultas

#### Como funciona:

**Cenário 1 - Uma consulta:**
1. Digite o CPF
2. Clique em "Pesquisar"
3. Detalhes da consulta são exibidos automaticamente

**Cenário 2 - Múltiplas consultas:**
1. Digite o CPF
2. Clique em "Pesquisar"
3. Aparece um dialog mostrando todas as consultas
4. Selecione a consulta desejada
5. Detalhes da consulta selecionada são exibidos

**Cenário 3 - Sem consultas:**
1. Digite o CPF
2. Clique em "Pesquisar"
3. Mensagem: "Paciente encontrado, mas não possui consultas cadastradas"

**Cenário 4 - Paciente não existe:**
1. Digite o CPF
2. Clique em "Pesquisar"
3. Mensagem: "Paciente não encontrado! CPF pesquisado: [CPF]"


---

## Data: 30/11/2025 - Parte 4

### Correção de Bugs e Melhorias

#### Bugs corrigidos:

1. **Programa fechando após agendar consulta**
   - Removido `dispose()` após salvar consulta
   - Agora a janela permanece aberta para agendar mais consultas
   - Mensagem atualizada informando que pode agendar outra ou fechar

2. **Campos não preenchidos nos detalhes da consulta**
   - Método `atualizarDadosConsulta()` atualizado
   - Agora preenche: Motivo da Consulta, Diagnóstico e Anotações do Médico
   - Trata valores nulos corretamente

#### Observações:

**Campo Diagnóstico na interface:**
- O campo `diagnosticoField` foi declarado mas não foi adicionado visualmente na tela
- Os dados de diagnóstico são salvos e recuperados corretamente do banco
- Para adicionar o campo visualmente seria necessário modificar o layout da tela de detalhes
- Atualmente o diagnóstico é exibido no campo que existe (se houver)

**Campos funcionando:**
- Motivo da Consulta (triagemArea) - ✓ Funcionando
- Anotações do Médico (medicacaoArea) - ✓ Funcionando  
- Diagnóstico - ✓ Salvo no banco, mas campo visual precisa ser adicionado ao layout

#### Arquivos modificados:
- `TelaAgendamentoConsulta.java` - Removido dispose() após salvar
- `TelaConsultas.java` - Atualizado método atualizarDadosConsulta()

# Implementações


---

## Data: 03/12/2025

### Sistema de Eventos Sentinelas

#### Funcionalidades implementadas:

**Backend:**

1. **Repositório EventoSentinelaRepositorio**
   - Criado repositório JPA para gerenciar eventos sentinelas
   - Métodos implementados:
     - `findByPaciente(Paciente)` - Busca eventos por paciente
     - `findByPacienteOrderByDataEventoDesc(Paciente)` - Busca eventos ordenados por data decrescente

2. **Serviço EventoSentinelaService**
   - Criado serviço para lógica de negócio de eventos
   - Métodos implementados:
     - `salvarEvento(EventoSentinela)` - Salva novo evento
     - `findEventosByPaciente(Paciente)` - Busca eventos de um paciente
     - `findAllEventos()` - Lista todos os eventos
     - `findEventoById(Long)` - Busca evento por ID

3. **Correção no modelo EventoSentinela**
   - Corrigido import do enum EventosOcorridos
   - Modelo já estava criado com todos os campos necessários

**Frontend:**

1. **Tela TelaEventosSentinelas**
   - Tela principal para gerenciar eventos sentinelas
   - Funcionalidades:
     - Campo CPF com formatação automática (###.###.###-##)
     - Busca de paciente por CPF
     - Exibição do nome do paciente encontrado
     - Tabela com lista de eventos do paciente (ID, Evento, Descrição, Data)
     - Botão "Adicionar Evento" para cadastrar novo evento
     - Botão "Voltar" para retornar ao painel principal
     - Mensagem amigável quando paciente não tem eventos cadastrados
     - Mensagem amigável quando CPF não é encontrado

2. **Tela TelaCadastroEventoSentinela**
   - Modal para cadastro de novos eventos
   - Funcionalidades:
     - Campo CPF com formatação automática e botão de busca
     - Campo Nome do Paciente (somente leitura, preenchido automaticamente)
     - ComboBox com lista de eventos disponíveis:
       - Tentativa de Suicídio
       - Quedas
       - Diarreia
       - Escabiose
       - Desidratação
       - Úlcera por Pressão
       - Desnutrição
       - Óbito
       - Pressão Arterial
       - Glicemia
       - Temperatura
     - Campo Descrição (texto livre, obrigatório)
     - Campo Data do Evento com formatação (DD/MM/AAAA)
     - Botões: Salvar, Limpar e Cancelar

3. **Validações implementadas**
   - CPF obrigatório e deve ter 11 dígitos
   - Paciente deve existir no banco de dados
   - Evento deve ser selecionado (não pode ser "Selecione um evento")
   - Descrição obrigatória
   - Data obrigatória e no formato correto
   - Mensagens de erro amigáveis para cada validação

4. **Integração com NavigationService**
   - Adicionado método `abrirTelaEventosSentinelas()`
   - Injeção de dependência da TelaEventosSentinelas

5. **Integração com TelaGeral**
   - Botão "Eventos Sentinelas" agora funcional
   - Ao clicar, abre a tela de eventos e fecha o painel principal

#### Arquivos criados:
- `EventoSentinelaRepositorio.java` - Repositório JPA
- `EventoSentinelaService.java` - Serviço de negócio
- `TelaEventosSentinelas.java` - Tela principal de eventos
- `TelaCadastroEventoSentinela.java` - Modal de cadastro

#### Arquivos modificados:
- `EventoSentinela.java` - Corrigido import do enum
- `NavigationService.java` - Adicionado método de navegação
- `TelaGeral.java` - Adicionada funcionalidade ao botão

#### Como funciona:

**Fluxo de uso:**

1. **Acessar a tela:**
   - No painel principal, clique em "Eventos Sentinelas"

2. **Buscar paciente:**
   - Digite o CPF do paciente (formatação automática)
   - Clique em "Buscar"
   - Sistema exibe o nome do paciente
   - Tabela mostra todos os eventos cadastrados
   - Se não houver eventos, exibe mensagem amigável

3. **Cadastrar novo evento:**
   - Clique em "Adicionar Evento"
   - Digite o CPF do paciente e clique em "Buscar"
   - Sistema exibe o nome do paciente
   - Selecione o tipo de evento no ComboBox
   - Digite a descrição (exemplo: "150" para glicemia, "sim" para suicídio)
   - Digite a data do evento (DD/MM/AAAA)
   - Clique em "Salvar"
   - Sistema valida todos os campos
   - Exibe mensagem de sucesso
   - Atualiza automaticamente a tabela de eventos
   - Fecha a modal

4. **Mensagens de erro:**
   - "Por favor, digite um CPF válido com 11 dígitos" - CPF incompleto
   - "Paciente não encontrado. Verifique o CPF digitado" - CPF não cadastrado
   - "Nenhum evento registrado para este paciente" - Paciente sem eventos
   - "Por favor, busque um paciente válido pelo CPF" - Tentou salvar sem buscar paciente
   - "Por favor, selecione um evento" - Não selecionou evento no ComboBox
   - "Por favor, preencha a descrição do evento" - Descrição vazia
   - "Por favor, preencha a data do evento" - Data incompleta

#### Exemplo de uso prático:

**Cenário 1 - Glicemia:**
- Evento: Glicemia
- Descrição: 150
- Data: 03/12/2025

**Cenário 2 - Tentativa de Suicídio:**
- Evento: Tentativa de Suicídio
- Descrição: sim
- Data: 01/12/2025

**Cenário 3 - Pressão Arterial:**
- Evento: Pressão Arterial
- Descrição: 140/90
- Data: 02/12/2025


---

## Data: 08/12/2025

### Refatoração da Tela de Eventos Sentinelas

#### Alterações realizadas:

1. **Novo layout seguindo o padrão da tela de Consultas**
   - Adicionado cabeçalho institucional usando `PanelsFactory`
   - Seção "Eventos Sentinelas" com botão "Novo Evento"
   - Painel "Parâmetros de Pesquisa" com campo CPF formatado
   - Seção "Detalhes do Evento" com scroll vertical
   - Rodapé institucional

2. **Campos de detalhes do evento**
   - Evento (ID)
   - Tipo de Evento
   - Data do Evento
   - Paciente (nome completo)
   - Descrição (área de texto)
   - Observações (área de texto)

3. **Funcionalidades implementadas**
   - Busca de paciente por CPF com formatação automática
   - Exibição de detalhes do evento em campos somente leitura
   - Seleção de múltiplos eventos quando o paciente possui mais de um
   - Botão "Atualizar" para limpar os campos
   - Mensagens amigáveis para diferentes situações (paciente não encontrado, sem eventos, etc.)

#### Arquivos modificados:
- `TelaEventosSentinelas.java` - Refatorada completamente seguindo o novo padrão

---

### Criação da Classe Utilitária CPFUtils

#### Problema identificado:
- Código repetitivo em várias telas para validação e formatação de CPF
- Mesma lógica de `DocumentFilter` duplicada em múltiplos arquivos
- Dificuldade de manutenção e inconsistências

#### Solução implementada:

**Criada classe `CPFUtils` no pacote `utils` com os seguintes métodos:**

1. **`limparCPF(String cpf)`**
   - Remove toda formatação do CPF (pontos e traço)
   - Retorna apenas os números
   - Exemplo: "123.456.789-01" → "12345678901"

2. **`formatarCPF(String cpf)`**
   - Adiciona formatação ao CPF (pontos e traço)
   - Exemplo: "12345678901" → "123.456.789-01"

3. **`validarTamanhoCPF(String cpf)`**
   - Valida se o CPF tem exatamente 11 dígitos
   - Retorna `true` ou `false`

4. **`aplicarFormatacaoAutomatica(JTextField textField)`**
   - Aplica formatação automática enquanto o usuário digita
   - Aceita apenas números
   - Limita a 11 dígitos
   - Adiciona pontos e traço automaticamente

#### Telas refatoradas para usar CPFUtils:
- `TelaConsultas.java`
- `TelaEventosSentinelas.java`
- `TelaCadastroEventoSentinela.java`
- `TelaCadastroPacientes.java`
- `TelaAgendamentoConsulta.java`

#### Benefícios:
- Código mais limpo e organizado
- Fácil manutenção (alterações em um único lugar)
- Consistência em todas as telas
- Código mais fácil de entender para iniciantes
- Redução de duplicação de código

#### Arquivos criados:
- `src/main/java/com/ProjetoExtensao/Projeto/utils/CPFUtils.java`

#### Exemplo de uso:

**Antes:**
```java
String cpf = txtCpf.getText().replaceAll("[^0-9]", "");
if (cpf.length() != 11) {
    // erro
}
```

**Depois:**
```java
String cpf = CPFUtils.limparCPF(txtCpf.getText());
if (!CPFUtils.validarTamanhoCPF(cpf)) {
    // erro
}
```

**Aplicar formatação automática:**
```java
// Antes: código complexo com DocumentFilter (60+ linhas)
// Depois: uma linha simples
CPFUtils.aplicarFormatacaoAutomatica(txtCpfField);
```
