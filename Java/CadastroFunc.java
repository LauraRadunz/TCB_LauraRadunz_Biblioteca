package Java;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CadastroFunc implements Serializable {

    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private static final String ARQUIVO = "funcionarios.dat";

    public CadastroFunc() {
        carregarFuncionarios();
    }

    public void cadastrarFuncionario(Funcionario f) {
        funcionarios.add(f);
        salvarFuncionarios();
    }

    public void listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado.");
        } else {
            StringBuilder mensagem = new StringBuilder("");
            for (Funcionario f : funcionarios) {
                mensagem.append("Código: ").append(f.getCodigo()).append(" | Nome: ").append(f.getNome()).append(" | Salário: ").append(f.getSalario()).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensagem.toString());
        }
    }

    public Funcionario buscarFuncionario(int codigo) {
        for (Funcionario f : funcionarios) {
            if (f.getCodigo() == codigo) {
                return f;
            }
        }
        return null;
    }

    public void imprimirFuncionario(Funcionario funcionario) {
        String mensagem = "Código: " + funcionario.getCodigo() + " | Nome: " + funcionario.getNome() + " | Salário: " + funcionario.getSalario()+"\n";
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void removerFuncionario(int codigo) {
        Funcionario funcionario = buscarFuncionario(codigo);
        if (funcionario != null) {
            funcionarios.remove(funcionario);
            salvarFuncionarios();
            JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso.");
            return; // remoção bem-sucedida
        }
        JOptionPane.showMessageDialog(null, "Funcionário com código " + codigo + " não encontrado.");
        return; // não encontrado
    }

    // ---- Persistência ----
    private void salvarFuncionarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(funcionarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarFuncionarios() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                funcionarios = (ArrayList<Funcionario>) ois.readObject();

            // Ajusta o contador para continuar do último código
            if (!funcionarios.isEmpty()) {
                int ultimoCodigo = funcionarios.get(funcionarios.size() - 1).getCodigo();
                Funcionario.setContador(ultimoCodigo + 1);
            }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
