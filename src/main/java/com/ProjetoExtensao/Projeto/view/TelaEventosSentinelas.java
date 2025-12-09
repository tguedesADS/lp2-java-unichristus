package com.ProjetoExtensao.Projeto.view;

import com.ProjetoExtensao.Projeto.infra.Cores;
import com.ProjetoExtensao.Projeto.infra.PanelsFactory;
import com.ProjetoExtensao.Projeto.models.EventoSentinela;
import com.ProjetoExtensao.Projeto.models.Paciente;
import com.ProjetoExtensao.Projeto.servicos.EventoSentinelaService;
import com.ProjetoExtensao.Projeto.servicos.NavigationService;
import com.ProjetoExtensao.Projeto.servicos.PacienteService;
import com.ProjetoExtensao.Projeto.utils.CPFUtils;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@NoArgsConstructor
public class TelaEventosSentinelas extends JFrame {
    @Autowired
    private PacienteService pacienteService;
    
    @Autowired
    private EventoSentinelaService eventoSentinelaService;
    
    @Autowired
    private NavigationService navigationService;
    
    @Autowired
    private TelaCadastroEventoSentinela telaCadastroEventoSentinela;
    
    @Autowired
    private PanelsFactory panelsFactory;

    private JTextField txtCpfBusca;
    private JTextField txtEventoId;
    private JTextField txtTipoEvento;
    private JTextField txtDataEvento;
    private JTextField txtPacienteNome;
    private JTextArea txtDescricao;
    private JTextArea txtObservacoes;
    private JButton refreshButton;
    private Paciente pacienteAtual = null;
    private List<EventoSentinela> eventosEncontrados;

    @PostConstruct
    public void initUI() {
        setTitle("Recanto do Sagrado Coração - Eventos Sentinelas");
        setSize(1200, 800);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Adicionar listener para limpar campos ao abrir
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                limparCamposPesquisa();
            }
        });

        Color azulEscuro = Cores.COR_RODAPE;
        Color cinzaTitulo = Cores.COR_LETRA_PAINEL;

        // CABEÇALHO INSTITUCIONAL
        JPanel headerPanel = panelsFactory.getHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);
        this.refreshButton = panelsFactory.getRefreshButton();

        // RODAPÉ
        JPanel footerPanel = panelsFactory.getFooterPanel();
        add(footerPanel, BorderLayout.SOUTH);

        // PAINEL CENTRAL
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Cores.COR_FUNDO_CLARO);
        contentPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        add(contentPanel, BorderLayout.CENTER);

        // CABEÇALHO DE SEÇÃO "Eventos Sentinelas"
        JPanel sectionHeader = new JPanel(new BorderLayout(10, 0));
        sectionHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Cores.COR_LETRA_PAINEL));
        sectionHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        sectionHeader.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sectionTitle = new JLabel("Eventos Sentinelas");
        sectionTitle.setFont(new Font("Arial", Font.PLAIN, 36));
        sectionTitle.setForeground(cinzaTitulo);
        sectionTitle.setBorder(new EmptyBorder(0, 20, 0, 0));
        sectionHeader.add(sectionTitle, BorderLayout.WEST);

        JPanel sectionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        sectionButtonsPanel.setOpaque(false);

        JButton novoEventoBtn = new JButton("Novo Evento");
        novoEventoBtn.setFont(new Font("Arial", Font.BOLD, 16));
        novoEventoBtn.setBackground(azulEscuro);
        novoEventoBtn.setForeground(Color.WHITE);
        novoEventoBtn.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        novoEventoBtn.setFocusPainted(false);
        sectionButtonsPanel.add(novoEventoBtn);

        novoEventoBtn.addActionListener(e -> abrirCadastroEvento());

        sectionHeader.add(sectionButtonsPanel, BorderLayout.EAST);
        contentPanel.add(sectionHeader);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // SEÇÃO "Parâmetros de Pesquisa"
        JPanel pesquisaPanel = new JPanel();
        pesquisaPanel.setLayout(new GridBagLayout());
        pesquisaPanel.setBackground(Cores.COR_FUNDO_CLARO);
        pesquisaPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        pesquisaPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        pesquisaPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        JPanel tituloPesquisaPanel = new JPanel(new BorderLayout());
        tituloPesquisaPanel.setBackground(Cores.COR_FUNDO_CLARO);
        tituloPesquisaPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
        JLabel paramPesquisaLabel = new JLabel("Parâmetros de Pesquisa");
        paramPesquisaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        paramPesquisaLabel.setForeground(new Color(0x556270));
        paramPesquisaLabel.setHorizontalAlignment(SwingConstants.LEFT);
        tituloPesquisaPanel.add(paramPesquisaLabel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        pesquisaPanel.add(tituloPesquisaPanel, gbc);

        gbc.gridwidth = 1;
        gbc.weightx = 0.5;

        JLabel cpfLabel = new JLabel("CPF do Paciente");
        cpfLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cpfLabel.setForeground(azulEscuro);
        pesquisaPanel.add(cpfLabel, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        txtCpfBusca = new JTextField();
        txtCpfBusca.setFont(new Font("Arial", Font.PLAIN, 16));
        txtCpfBusca.setForeground(azulEscuro);
        CPFUtils.aplicarFormatacaoAutomatica(txtCpfBusca);
        addPlaceholder(txtCpfBusca, "123.456.789-01");
        pesquisaPanel.add(txtCpfBusca, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.0;
        JButton pesquisarBtn = new JButton("Pesquisar");
        pesquisarBtn.setFont(new Font("Arial", Font.BOLD, 14));
        pesquisarBtn.setBackground(azulEscuro);
        pesquisarBtn.setForeground(Color.WHITE);
        pesquisarBtn.setFocusPainted(false);
        pesquisarBtn.setBorder(new EmptyBorder(8, 15, 8, 15));
        pesquisaPanel.add(pesquisarBtn, gbc);

        contentPanel.add(pesquisaPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // SEÇÃO "Detalhes do Evento"
        JPanel detalhesPanel = new JPanel();
        detalhesPanel.setLayout(new GridBagLayout());
        detalhesPanel.setBackground(Color.WHITE);
        detalhesPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        detalhesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel detalhesTituloLabel = new JLabel("Detalhes do Evento");
        detalhesTituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        detalhesTituloLabel.setForeground(azulEscuro);
        detalhesTituloLabel.setHorizontalAlignment(SwingConstants.LEFT);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, 15, 0);
        detalhesPanel.add(detalhesTituloLabel, gbc);

        Color borderColor = new Color(200, 200, 200);

        // Linha 1: ID Evento, Tipo de Evento, Data
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.33;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 0, 0);
        JLabel eventoIdLabel = new JLabel("Evento:");
        eventoIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
        eventoIdLabel.setForeground(azulEscuro);
        detalhesPanel.add(eventoIdLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.33;
        gbc.insets = new Insets(10, 10, 0, 0);
        JLabel tipoEventoLabel = new JLabel("Tipo de Evento:");
        tipoEventoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        tipoEventoLabel.setForeground(azulEscuro);
        detalhesPanel.add(tipoEventoLabel, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.34;
        JLabel dataEventoLabel = new JLabel("Data do Evento:");
        dataEventoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dataEventoLabel.setForeground(azulEscuro);
        detalhesPanel.add(dataEventoLabel, gbc);

        // Campos da Linha 1
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 2;
        txtEventoId = new JTextField();
        txtEventoId.setFont(new Font("Arial", Font.PLAIN, 16));
        txtEventoId.setBackground(Color.WHITE);
        txtEventoId.setEditable(false);
        txtEventoId.setBorder(BorderFactory.createLineBorder(borderColor));
        detalhesPanel.add(txtEventoId, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 10, 10, 0);
        txtTipoEvento = new JTextField();
        txtTipoEvento.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTipoEvento.setBackground(Color.WHITE);
        txtTipoEvento.setEditable(false);
        txtTipoEvento.setBorder(BorderFactory.createLineBorder(borderColor));
        detalhesPanel.add(txtTipoEvento, gbc);

        gbc.gridx = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        txtDataEvento = new JTextField();
        txtDataEvento.setFont(new Font("Arial", Font.PLAIN, 16));
        txtDataEvento.setBackground(Color.WHITE);
        txtDataEvento.setEditable(false);
        txtDataEvento.setBorder(BorderFactory.createLineBorder(borderColor));
        detalhesPanel.add(txtDataEvento, gbc);

        // Linha 2: Paciente
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 0, 0);
        JLabel pacienteLabel = new JLabel("Paciente:");
        pacienteLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pacienteLabel.setForeground(azulEscuro);
        detalhesPanel.add(pacienteLabel, gbc);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 20, 0);
        txtPacienteNome = new JTextField();
        txtPacienteNome.setFont(new Font("Arial", Font.PLAIN, 16));
        txtPacienteNome.setBackground(Color.WHITE);
        txtPacienteNome.setEditable(false);
        txtPacienteNome.setBorder(BorderFactory.createLineBorder(borderColor));
        detalhesPanel.add(txtPacienteNome, gbc);

        // Linha 3: Descrição
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 0, 0, 0);
        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        descricaoLabel.setForeground(azulEscuro);
        detalhesPanel.add(descricaoLabel, gbc);

        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.weighty = 0.5;
        txtDescricao = new JTextArea(5, 20);
        txtDescricao.setFont(new Font("Arial", Font.PLAIN, 16));
        txtDescricao.setBackground(Color.WHITE);
        txtDescricao.setLineWrap(true);
        txtDescricao.setWrapStyleWord(true);
        txtDescricao.setEditable(false);
        txtDescricao.setBorder(BorderFactory.createLineBorder(borderColor));
        detalhesPanel.add(txtDescricao, gbc);

        // Linha 4: Observações
        gbc.gridy = 7;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(10, 0, 0, 0);
        JLabel observacoesLabel = new JLabel("Observações:");
        observacoesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        observacoesLabel.setForeground(azulEscuro);
        detalhesPanel.add(observacoesLabel, gbc);

        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.weighty = 0.5;
        txtObservacoes = new JTextArea(5, 20);
        txtObservacoes.setFont(new Font("Arial", Font.PLAIN, 16));
        txtObservacoes.setBackground(Color.WHITE);
        txtObservacoes.setLineWrap(true);
        txtObservacoes.setWrapStyleWord(true);
        txtObservacoes.setEditable(false);
        txtObservacoes.setBorder(BorderFactory.createLineBorder(borderColor));
        detalhesPanel.add(txtObservacoes, gbc);

        // Adicionar scroll ao painel de detalhes
        JScrollPane scrollPane = new JScrollPane(detalhesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        contentPanel.add(scrollPane);

        // Ação do botão Atualizar
        if (refreshButton != null) {
            refreshButton.addActionListener(e -> {
                limparCamposDetalhes();
                txtCpfBusca.setText("");
                addPlaceholder(txtCpfBusca, "123.456.789-01");
            });
        }

        // Ação do botão Pesquisar
        pesquisarBtn.addActionListener(e -> buscarPaciente());
    }

    private void buscarPaciente() {
        String cpf = txtCpfBusca.getText();

        if (cpf.isEmpty() || cpf.equals("123.456.789-01")) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, digite um CPF para pesquisar.", 
                "Aviso", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String cpfLimpo = CPFUtils.limparCPF(cpf);
            
            if (!CPFUtils.validarTamanhoCPF(cpfLimpo)) {
                JOptionPane.showMessageDialog(this, 
                    "CPF inválido. Digite um CPF com 11 dígitos.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Paciente paciente = null;
            try {
                paciente = pacienteService.findPacienteByCpf(cpfLimpo);
            } catch (RuntimeException ex) {
                limparCamposDetalhes();
                JOptionPane.showMessageDialog(this, 
                    "Paciente não encontrado!\n\nCPF pesquisado: " + cpfLimpo + "\n\nVerifique se o paciente está cadastrado.", 
                    "Paciente não encontrado", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            eventosEncontrados = eventoSentinelaService.findEventosByPaciente(paciente);
            
            if (eventosEncontrados == null || eventosEncontrados.isEmpty()) {
                limparCamposDetalhes();
                JOptionPane.showMessageDialog(this, 
                    "Paciente encontrado, mas não possui eventos sentinelas cadastrados.\n\nPaciente: " + paciente.getNomeCompleto(), 
                    "Sem eventos", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else if (eventosEncontrados.size() == 1) {
                atualizarDadosEvento(eventosEncontrados.get(0));
            } else {
                mostrarListaEventos(eventosEncontrados, paciente);
            }
        } catch (RuntimeException ex) {
            limparCamposDetalhes();
            JOptionPane.showMessageDialog(this, 
                "Erro ao buscar evento:\n" + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarDadosEvento(EventoSentinela evento) {
        txtEventoId.setText(evento.getId().toString());
        txtTipoEvento.setText(formatarNomeEvento(evento.getEventosOcorridos().name()));
        txtDataEvento.setText(evento.getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtPacienteNome.setText(evento.getPaciente().getNomeCompleto());
        
        if (txtDescricao != null) {
            txtDescricao.setText(evento.getDescricao() != null ? evento.getDescricao() : "");
        }
        if (txtObservacoes != null) {
            txtObservacoes.setText(evento.getObservacoes() != null ? evento.getObservacoes() : "");
        }
    }

    private String formatarNomeEvento(String nomeEnum) {
        return nomeEnum.replace("_", " ").toLowerCase()
            .substring(0, 1).toUpperCase() + 
            nomeEnum.replace("_", " ").toLowerCase().substring(1);
    }

    private void limparCamposDetalhes() {
        txtEventoId.setText("");
        txtTipoEvento.setText("");
        txtDataEvento.setText("");
        txtPacienteNome.setText("");
        txtDescricao.setText("");
        txtObservacoes.setText("");

        if (txtEventoId != null) txtEventoId.setForeground(Cores.COR_RODAPE);
        if (txtTipoEvento != null) txtTipoEvento.setForeground(Cores.COR_RODAPE);
        if (txtDataEvento != null) txtDataEvento.setForeground(Cores.COR_RODAPE);
        if (txtPacienteNome != null) txtPacienteNome.setForeground(Cores.COR_RODAPE);
        if (txtDescricao != null) txtDescricao.setForeground(Cores.COR_RODAPE);
        if (txtObservacoes != null) txtObservacoes.setForeground(Cores.COR_RODAPE);
    }

    private void addPlaceholder(JTextField textField, String placeholder) {
        Color placeholderColor = new Color(150, 150, 150);
        Color originalColor = Cores.COR_RODAPE;

        textField.setText(placeholder);
        textField.setForeground(placeholderColor);

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(originalColor);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(placeholderColor);
                }
            }
        });
    }

    private void aplicarFormatacaoCPF(JTextField textField) {
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                String newStr = string.replaceAll("[^0-9]", "");
                if (newStr.isEmpty()) return;
                
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String textSemFormatacao = currentText.replaceAll("[^0-9]", "");
                
                if (textSemFormatacao.length() + newStr.length() <= 11) {
                    super.insertString(fb, offset, newStr, attr);
                    formatarCPFNoTexto(fb);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String newStr = text.replaceAll("[^0-9]", "");
                
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                String textSemFormatacao = currentText.replaceAll("[^0-9]", "");
                
                String textoRemovido = currentText.substring(offset, Math.min(offset + length, currentText.length()));
                int digitosRemovidos = textoRemovido.replaceAll("[^0-9]", "").length();
                
                if (textSemFormatacao.length() - digitosRemovidos + newStr.length() <= 11) {
                    super.replace(fb, offset, length, newStr, attrs);
                    formatarCPFNoTexto(fb);
                }
            }

            @Override
            public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
                formatarCPFNoTexto(fb);
            }

            private void formatarCPFNoTexto(FilterBypass fb) throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                String apenasNumeros = text.replaceAll("[^0-9]", "");
                
                StringBuilder formatted = new StringBuilder();
                for (int i = 0; i < apenasNumeros.length(); i++) {
                    if (i == 3 || i == 6) {
                        formatted.append(".");
                    } else if (i == 9) {
                        formatted.append("-");
                    }
                    formatted.append(apenasNumeros.charAt(i));
                }
                
                if (!text.equals(formatted.toString())) {
                    fb.remove(0, fb.getDocument().getLength());
                    fb.insertString(0, formatted.toString(), null);
                }
            }
        });
    }

    private void limparCamposPesquisa() {
        if (txtCpfBusca != null) {
            txtCpfBusca.setText("");
            addPlaceholder(txtCpfBusca, "123.456.789-01");
        }
        limparCamposDetalhes();
    }

    private void mostrarListaEventos(List<EventoSentinela> eventos, Paciente paciente) {
        String[] opcoes = new String[eventos.size()];
        for (int i = 0; i < eventos.size(); i++) {
            EventoSentinela e = eventos.get(i);
            opcoes[i] = String.format("Evento %d - %s - %s", 
                e.getId(),
                formatarNomeEvento(e.getEventosOcorridos().name()),
                e.getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        
        String escolha = (String) JOptionPane.showInputDialog(
            this,
            "Paciente: " + paciente.getNomeCompleto() + "\n\nEste paciente possui " + eventos.size() + " eventos.\nSelecione um para ver os detalhes:",
            "Selecionar Evento",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcoes,
            opcoes[0]
        );
        
        if (escolha != null) {
            for (int i = 0; i < opcoes.length; i++) {
                if (opcoes[i].equals(escolha)) {
                    atualizarDadosEvento(eventos.get(i));
                    break;
                }
            }
        }
    }

    private void abrirCadastroEvento() {
        telaCadastroEventoSentinela.limparCamposAoAbrir();
        telaCadastroEventoSentinela.setVisible(true);
    }

    public void setCpfBusca(String cpf) {
        txtCpfBusca.setText(cpf);
        buscarPaciente();
    }

    public void limparCampos() {
        limparCamposPesquisa();
        pacienteAtual = null;
    }
}
