package View;

import Controller.funcionarioController;
import Model.funcionario;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaListagem extends JFrame {
    private JTable tabela;
    private funcionarioController controller;

    public TelaListagem(funcionarioController controller) {
        this.controller = controller;

        setTitle("Lista de Funcionários");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel lblTitulo = new JLabel("Lista de Funcionários", JLabel.CENTER);
        lblTitulo.setFont(new Font("Cambria", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"ID", "Nome", "Cargo", "Salário"};
        List<funcionario> listaFuncionarios = controller.listarFuncionarios();
        Object[][] dados = new Object[listaFuncionarios.size()][4];

        for (int i = 0; i < listaFuncionarios.size(); i++) {
            funcionario func = listaFuncionarios.get(i);
            dados[i][0] = func.getId();
            dados[i][1] = func.getNome();
            dados[i][2] = func.getCargo();
            dados[i][3] = func.getSalario();
        }

        tabela = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        // Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> dispose());
        add(btnVoltar, BorderLayout.SOUTH);
    }
}
