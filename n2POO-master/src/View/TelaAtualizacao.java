package View;

import Controller.funcionarioController;
import Model.funcionario;

import javax.swing.*;
import java.awt.*;

public class TelaAtualizacao extends JFrame {
    private JTextField txtId, txtNome, txtCargo, txtSalario;
    private JButton btnBuscar, btnAtualizar, btnVoltar;
    private funcionarioController controller;

    public TelaAtualizacao(funcionarioController controller) {
        this.controller = controller;

        setTitle("Atualizar Funcionário");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título
        JLabel lblTitulo = new JLabel("Atualizar Funcionário", JLabel.CENTER);
        lblTitulo.setFont(new Font("Cambria", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // Formulário
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelForm.add(txtId);

        panelForm.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panelForm.add(txtNome);

        panelForm.add(new JLabel("Cargo:"));
        txtCargo = new JTextField();
        panelForm.add(txtCargo);

        panelForm.add(new JLabel("Salário:"));
        txtSalario = new JTextField();
        panelForm.add(txtSalario);

        add(panelForm, BorderLayout.CENTER);

        // Botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnBuscar = new JButton("Buscar");
        btnAtualizar = new JButton("Atualizar");
        btnVoltar = new JButton("Voltar");

        panelBotoes.add(btnBuscar);
        panelBotoes.add(btnAtualizar);
        panelBotoes.add(btnVoltar);

        add(panelBotoes, BorderLayout.SOUTH);

        // Eventos
        btnBuscar.addActionListener(e -> buscarFuncionario());
        btnAtualizar.addActionListener(e -> atualizarFuncionario());
        btnVoltar.addActionListener(e -> dispose());
    }

    private void buscarFuncionario() {
        try {
            int id = Integer.parseInt(txtId.getText());
            funcionario func = controller.buscarFuncionarioPorId(id);
            if (func != null) {
                txtNome.setText(func.getNome());
                txtCargo.setText(func.getCargo());
                txtSalario.setText(String.valueOf(func.getSalario()));
            } else {
                JOptionPane.showMessageDialog(this, "Funcionário não encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar funcionário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarFuncionario() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nome = txtNome.getText();
            String cargo = txtCargo.getText();
            double salario = Double.parseDouble(txtSalario.getText());

            if (controller.atualizarFuncionario(id, nome, cargo, salario)) {
                JOptionPane.showMessageDialog(this, "Funcionário atualizado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro: Funcionário não encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar funcionário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
