package Java;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CadastroClientes {
    private ArrayList<Cliente> clientes = new ArrayList<>();    
    private static final String ARQUIVO = "clientes.dat";

    public CadastroClientes() {
        carregarClientes(); // Vai carregar na lista clientes os clientes que estão no arquivo
    }

    public void cadastrarCliente(Cliente x) {
        clientes.add(x); 
        salvarClientes(); // Vai salvar a lista clientes no arquivo
    }

    public Cliente buscarCliente (int registro) {
        for (Cliente cliente: clientes) {
            if (cliente.getRegistro() == registro) {
                return cliente; // Retorna o cliente com o registro correspondente
            }
        }
        return null; // Se não há um cliente com esse registro, retorna nulo
    }

    public void removerCliente(int registro) {
        Cliente cliente = buscarCliente(registro);
        if (cliente != null) {
            clientes.remove(cliente);
            salvarClientes();
            JOptionPane.showMessageDialog(null, "Cliente removido com sucesso.");
            return; // remoção bem-sucedida
        }
        JOptionPane.showMessageDialog(null, "Cliente com registro " + registro + " não encontrado.");
        return; // não encontrado
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado.");
        } else {
            StringBuilder mensagem = new StringBuilder("Clientes Cadastrados:\n");
            for (Cliente c : clientes) {
                mensagem.append("Registro: ").append(c.getRegistro()).append(" | Nome: ").append(c.getNome())
                .append(" | Telefone: ").append(c.getTelefone()).append(" | CPF: ").append(c.getCpf()).append("\n");
            }
            JOptionPane.showMessageDialog(null, mensagem.toString());
        }
    }

    // ---- Persistência ----
    private void salvarClientes() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(clientes);
        } catch (IOException c) {
            c.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarClientes() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                clientes = (ArrayList<Cliente>) ois.readObject();

            // Ajusta o contador para continuar do último código
            if (!clientes.isEmpty()) {
                int ultimoCodigo = clientes.get(clientes.size() - 1).getRegistro();
                Cliente.setContador(ultimoCodigo + 1);
            }

            } catch (IOException | ClassNotFoundException c) {
                c.printStackTrace();
            }
        }
    }

}
