package View;

import Controller.funcionarioController;

import javax.swing.*;
import java.awt.*;

public class ViewSwing extends JFrame {
    private JLabel lblTitulo, lblInstrucao;
    private JButton btnCadastrar, btnListar, btnAtualizar, btnExcluir;
    private funcionarioController controller;

    public ViewSwing(funcionarioController controller) {
        this.controller = controller;

        setTitle("Gestão de Funcionários");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new BorderLayout(10, 10)); // Espaçamento entre os painéis

        // Painel Superior - Título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(70, 130, 180)); // Azul suave
        lblTitulo = new JLabel("Painel de Gestão");
        lblTitulo.setFont(new Font("Cambria", Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);

        // Painel Central - Instrução
        lblInstrucao = new JLabel("Selecione uma opção abaixo:", JLabel.CENTER);
        lblInstrucao.setFont(new Font("Arial", Font.PLAIN, 16));
        lblInstrucao.setForeground(new Color(60, 60, 60));

        // Painel Inferior - Botões
        JPanel panelBotoes = new JPanel(new GridBagLayout());
        panelBotoes.setBackground(Color.WHITE);

        btnCadastrar = criarBotao("Cadastrar Funcionário");
        btnListar = criarBotao("Listar Funcionários");
        btnAtualizar = criarBotao("Atualizar Funcionário");
        btnExcluir = criarBotao("Excluir Funcionário");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os botões

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelBotoes.add(btnCadastrar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelBotoes.add(btnListar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelBotoes.add(btnAtualizar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelBotoes.add(btnExcluir, gbc);

        // Adicionar os painéis ao JFrame
        add(panelTitulo, BorderLayout.NORTH);
        add(lblInstrucao, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        configurarEventos();
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.PLAIN, 14));
        botao.setBackground(new Color(100, 149, 237)); // Azul claro
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }

    private void configurarEventos() {
        // Botão Cadastrar
        btnCadastrar.addActionListener(e -> {
            TelaCadastro telaCadastro = new TelaCadastro(controller);
            telaCadastro.setVisible(true);
        });

        // Botão Listar
        btnListar.addActionListener(e -> {
            TelaListagem telaListagem = new TelaListagem(controller);
            telaListagem.setVisible(true);
        });

        // Botão Atualizar
        btnAtualizar.addActionListener(e -> {
            TelaAtualizacao telaAtualizacao = new TelaAtualizacao(controller);
            telaAtualizacao.setVisible(true);
        });

        // Botão Excluir
        btnExcluir.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(this, "Digite o ID do funcionário para excluir:");
            if (idStr != null && !idStr.trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    if (controller.excluirFuncionario(id)) {
                        JOptionPane.showMessageDialog(this, "Funcionário excluído com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Funcionário não encontrado!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        funcionarioController controller = new funcionarioController();
        SwingUtilities.invokeLater(() -> new ViewSwing(controller).setVisible(true));
    }
}
