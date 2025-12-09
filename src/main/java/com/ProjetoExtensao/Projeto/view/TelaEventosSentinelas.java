package com.ProjetoExtensao.Projeto.view;

import com.ProjetoExtensao.Projeto.infra.Cores;
import com.ProjetoExtensao.Projeto.models.EventoSentinela;
import com.ProjetoExtensao.Projeto.models.Paciente;
import com.ProjetoExtensao.Projeto.servicos.EventoSentinelaService;
import com.ProjetoExtensao.Projeto.servicos.NavigationService;
import com.ProjetoExtensao.Projeto.servicos.PacienteService;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
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

    private JFormattedTextField txtCpfBusca;
    private JTable tabelaEventos;
    private DefaultTableModel modeloTabela;
    private JLabel lblNomePaciente;
    private Paciente pacienteAtual = null;

    @PostConstruct
    public void initUI() {
        setTitle("Eventos Sentinelas");
        setSize(1000, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Adicionar listener para limpar campos ao fechar
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                limparCampos();
            }
        });

        JPanel panelMain = new JPanel(new BorderLayout(10, 10));
        panelMain.setBackground(Cores.COR_FUNDO_CLARO);
        panelMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel superior - Busca por CPF
        JPanel panelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelBusca.setBackground(Cores.COR_FUNDO_CLARO);

        JLabel lblCpf = new JLabel("CPF do Paciente:");
        lblCpf.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtCpfBusca = createFormattedTextField("###.###.###-##");
        txtCpfBusca.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JButton btnBuscar = createButton("Buscar");
        btnBuscar.addActionListener(e -> buscarPaciente());

        JButton btnAdicionarEvento = createButton("Adicionar Evento");
        btnAdicionarEvento.addActionListener(e -> abrirCadastroEvento());

        JButton btnVoltar = createButton("Voltar");
        btnVoltar.addActionListener(e -> {
            limparCampos();
            navigationService.abrirTelaGeral();
            dispose();
        });

        panelBusca.add(lblCpf);
        panelBusca.add(txtCpfBusca);
        panelBusca.add(btnBuscar);
        panelBusca.add(btnAdicionarEvento);
        panelBusca.add(btnVoltar);

        // Label para exibir nome do paciente
        lblNomePaciente = new JLabel("");
        lblNomePaciente.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNomePaciente.setForeground(Cores.COR_RODAPE);
        lblNomePaciente.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel central - Tabela de eventos
        String[] colunas = {"ID", "Evento", "Descrição", "Data"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaEventos = new JTable(modeloTabela);
        tabelaEventos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabelaEventos.setRowHeight(25);
        tabelaEventos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tabelaEventos.getTableHeader().setBackground(Cores.COR_RODAPE);
        tabelaEventos.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tabelaEventos);

        panelMain.add(panelBusca, BorderLayout.NORTH);
        panelMain.add(lblNomePaciente, BorderLayout.CENTER);
        panelMain.add(scrollPane, BorderLayout.SOUTH);

        add(panelMain, BorderLayout.CENTER);
    }

    private JFormattedTextField createFormattedTextField(String mask) {
        JFormattedTextField txt = null;
        try {
            MaskFormatter formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
            txt = new JFormattedTextField(formatter);
            txt.setColumns(15);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return txt;
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(Cores.COR_RODAPE);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return btn;
    }

    private void buscarPaciente() {
        String cpf = txtCpfBusca.getText().replaceAll("[^0-9]", "");
        
        if (cpf.length() != 11) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, digite um CPF válido com 11 dígitos.", 
                "CPF Inválido", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            pacienteAtual = pacienteService.findPacienteByCpf(cpf);
            lblNomePaciente.setText("Paciente: " + pacienteAtual.getNomeCompleto());
            atualizarTabelaEventos();
        } catch (RuntimeException e) {
            pacienteAtual = null;
            lblNomePaciente.setText("");
            limparTabela();
            JOptionPane.showMessageDialog(this, 
                "Paciente não encontrado. Verifique o CPF digitado.", 
                "Paciente Não Encontrado", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void atualizarTabelaEventos() {
        limparTabela();
        
        if (pacienteAtual == null) {
            return;
        }

        List<EventoSentinela> eventos = eventoSentinelaService.findEventosByPaciente(pacienteAtual);
        
        if (eventos.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Nenhum evento registrado para este paciente.", 
                "Sem Eventos", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        for (EventoSentinela evento : eventos) {
            Object[] linha = {
                evento.getId(),
                formatarNomeEvento(evento.getEventosOcorridos().name()),
                evento.getDescricao(),
                evento.getDataEvento().format(formatter)
            };
            modeloTabela.addRow(linha);
        }
    }

    private String formatarNomeEvento(String nomeEnum) {
        return nomeEnum.replace("_", " ").toLowerCase()
            .substring(0, 1).toUpperCase() + 
            nomeEnum.replace("_", " ").toLowerCase().substring(1);
    }

    private void limparTabela() {
        modeloTabela.setRowCount(0);
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
        txtCpfBusca.setText("");
        lblNomePaciente.setText("");
        limparTabela();
        pacienteAtual = null;
    }
}
