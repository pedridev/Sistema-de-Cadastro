package Controller;

import Model.funcionario;

import java.util.ArrayList;
import java.util.List;

public class funcionarioController {
    private List<funcionario> funcionarios = new ArrayList<>();
    private int contadorId = 1;

    public void cadastrarFuncionario(String nome, String cargo, double salario) {
        funcionario func = new funcionario(contadorId++, nome, cargo, salario);
        funcionarios.add(func);
    }

    public List<funcionario> listarFuncionarios() {
        return funcionarios;
    }

    public funcionario buscarFuncionarioPorId(int id) {
        for (funcionario func : funcionarios) {
            if (func.getId() == id) {
                return func;
            }
        }
        return null;
    }

    public boolean atualizarFuncionario(int id, String nome, String cargo, double salario) {
        funcionario func = buscarFuncionarioPorId(id);
        if (func != null) {
            func.setNome(nome);
            func.setCargo(cargo);
            func.setSalario(salario);
            return true;
        }
        return false;
    }

    public boolean excluirFuncionario(int id) {
        funcionario func = buscarFuncionarioPorId(id);
        if (func != null) {
            funcionarios.remove(func);
            return true;
        }
        return false;
    }
}
