package Java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CadastroEmprestimos {
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private static final String ARQUIVO = "emprestimos.dat";

    public CadastroEmprestimos() {
        carregarEmprestimos();
    }

    public void devolverLivro (int codigoEmprestimo) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCodEmprestimo() == codigoEmprestimo  && !emprestimo.isDevolvido()) { // Verifica se o código do empréstimo é igual ao que foi passado e se o livro não foi devolvido ainda
                String dataStr = JOptionPane.showInputDialog("Digite a data de devolução (dd/MM/yyyy):");
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataDigitada = LocalDate.parse(dataStr, formato);
                
                Livro livro = emprestimo.getLivro();
                boolean temComoDevolver =  livro.devolver(); // Emprestados --

                if (temComoDevolver == true) {
                    emprestimo.setDevolvido(true); // Marca o empréstimo como devolvido
                    emprestimo.setDataDevolucaoEfetiva(dataDigitada);
    
                    if (emprestimo.getDataDevolucaoEfetiva().isAfter(emprestimo.getDataDevolucaoPrevista())) {
                    long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoPrevista(), emprestimo.getDataDevolucaoEfetiva());
                    double multa = diasAtraso * 0.50; // Vai cobrar R$0,50 por dia de atraso
                    JOptionPane.showMessageDialog(null, "Devolução feita com multa de R$" + multa + " por " + diasAtraso + " dias de atraso.");
                    } else {
                    JOptionPane.showMessageDialog(null, "Devolução realizada no prazo. Sem multa." );	
                    }
                    salvarEmprestimos();
                    return;
                }
            }
        }
    }

    public Emprestimo buscarEmprestimoPorCodigo(int codigoEmprestimo) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCodEmprestimo() == codigoEmprestimo) {
                return emprestimo;
            }
        }
        return null; 
    }

    public void realizarEmprestimos(Emprestimo x) {
        emprestimos.add(x); // Adiciona um empréstimo à uma lista de empréstimos
        salvarEmprestimos();
    }

    public CadastroEmprestimos buscarEmprestimo(int codCliente) {
        CadastroEmprestimos encontrados = new CadastroEmprestimos();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCliente().getRegistro() == codCliente) {
                if (!emprestimo.isDevolvido()) {
                    encontrados.realizarEmprestimos(emprestimo); // Não vai realizar um empréstimo, mas vai aproveitar a função que já está pronta
                }
            }
        }
        return encontrados;
    }

    public void listarEmprestimos(String tipo) {
        if (emprestimos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum empréstimo em aberto.");
            return;
        }
        StringBuilder mensagem = new StringBuilder(tipo+":\n");
        for (Emprestimo emp : emprestimos) {
            if (!emp.isDevolvido()) {
                mensagem.append("Código: ").append(emp.getCodEmprestimo())
                .append(" | Título: ").append(emp.getLivro().getNome()).append("\n | Cliente: ").append(emp.getCliente().getNome()).append("\n | Funcionário: ").append(emp.getFuncionario().getNome())
                .append("\n"); 
            }       
        }
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void renovarEmprestimo(int codigoEmprestimo) {
        Emprestimo emprestimo = emprestimos.get(codigoEmprestimo);
        if (emprestimo != null) {
            if (emprestimo.isDevolvido()) {
                JOptionPane.showMessageDialog(null, "Empréstimo já foi devolvido. Não é possível renovar.");
                return;
            }
                emprestimo.setDataDevolucaoPrevista(emprestimo.getDataDevolucaoPrevista().plusDays(15)); // Adiciona mais 15 dias à data prevista
                emprestimo.setRenovacoes(emprestimo.getRenovacoes() + 1);
                JOptionPane.showMessageDialog(null, "Empréstimo renovado com sucesso! Nova data de devolução: " + emprestimo.getDataDevolucaoPrevista().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                salvarEmprestimos();
                return;
        } else {
            JOptionPane.showMessageDialog(null, "Empréstimo não encontrado.");
        }
    }

    public void removerEmprestimo(int codigoEmprestimo) {
        Emprestimo emprestimo = buscarEmprestimoPorCodigo(codigoEmprestimo);
        if (emprestimo != null) {
            emprestimos.remove(emprestimo);
            salvarEmprestimos();
            JOptionPane.showMessageDialog(null, "Empréstimo removido com sucesso.");
            salvarEmprestimos();
            return; // remoção bem-sucedida
        }
        JOptionPane.showMessageDialog(null, "Empréstimo com código " + codigoEmprestimo + " não encontrado.");
        return; // não encontrado
    }

    // ---- Persistência ----
    private void salvarEmprestimos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(emprestimos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarEmprestimos() {
        File arquivo = new File(ARQUIVO);
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                emprestimos = (ArrayList<Emprestimo>) ois.readObject();

            // Ajusta o contador para continuar do último código
            if (!emprestimos.isEmpty()) {
                int ultimoCodigo = emprestimos.get(emprestimos.size() - 1).getCodEmprestimo();
                Emprestimo.setContador(ultimoCodigo + 1);
            }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
