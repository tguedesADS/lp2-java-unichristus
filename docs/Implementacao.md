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
