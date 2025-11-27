# Registro de Implementações

## Data: 27/11/2024

### Refatoração da Tela de Pacientes

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

## Data: 27/11/2024 - Parte 2

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

## Data: 27/11/2024 - Parte 3

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

## Data: 27/11/2024 - Parte 4

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
