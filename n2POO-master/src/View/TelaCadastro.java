package View;

import Controller.funcionarioController;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JFrame {
    private JTextField txtNome, txtCargo, txtSalario;
    private JButton btnSalvar, btnVoltar;
    private funcionarioController controller;

    public TelaCadastro(funcionarioController controller) {
        this.controller = controller;

        setTitle("Cadastro de Funcionário");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel Superior
        JLabel lblTitulo = new JLabel("Cadastro de Funcionário", JLabel.CENTER);
        lblTitulo.setFont(new Font("Cambria", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // Painel Central
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        // Painel Inferior
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnSalvar = new JButton("Salvar");
        btnVoltar = new JButton("Voltar");

        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnVoltar);

        add(panelBotoes, BorderLayout.SOUTH);

        // Eventos
        btnSalvar.addActionListener(e -> salvarFuncionario());
        btnVoltar.addActionListener(e -> dispose());
    }

    private void salvarFuncionario() {
        try {
            String nome = txtNome.getText();
            String cargo = txtCargo.getText();
            double salario = Double.parseDouble(txtSalario.getText());

            controller.cadastrarFuncionario(nome, cargo, salario);
            JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar funcionário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
