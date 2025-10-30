package Java;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.io.Serializable;

public class Emprestimo implements Serializable {
    private Livro livro;
    private Cliente cliente;
    private Funcionario funcionario;
    private int codEmprestimo;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoEfetiva;
    private boolean devolvido;
    private int renovacoes;

    private static int contador = 4001;

    public static void setContador(int valor) {
        contador = valor;
    }

    public Emprestimo(Livro livro, Cliente cliente, Funcionario funcionario) {
        this.livro = livro;
        boolean podeEmprestar = livro.emprestar();
        if (podeEmprestar == false) {
            JOptionPane.showMessageDialog(null, "Empréstimo não realizado. Livro indisponível.");
            return;
        }
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.codEmprestimo = codEmprestimo + contador; 
        this.dataEmprestimo = LocalDate.now(); // data atual
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(15); // prazo de 15 dias para devolução
        this.devolvido = false;
        this.renovacoes = 0;
        contador++;

        JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso! Código do empréstimo: " + this.codEmprestimo);
    }

    public void imprimirEmprestimo(Emprestimo emprestimo) {
        String mensagem = "Código: " + emprestimo.getCodEmprestimo() + " | Livro: " + emprestimo.getLivro().getNome() + " | Autor: " + emprestimo.getLivro().getAutor()+"\n" +
                            "Cliente: " + emprestimo.getCliente().getNome() + " | Registro do cliente: " + emprestimo.getCliente().getRegistro() + 
                            "\nFuncionário: " + emprestimo.getFuncionario().getNome() + " | Código do funcionário: " + emprestimo.getFuncionario().getCodigo()+"\n" +
                            "Data do empréstimo: " + emprestimo.getDataEmprestimo() + " | Data prevista para devolução: " + emprestimo.getDataDevolucaoPrevista();     
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
        
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getCodEmprestimo() {
        return codEmprestimo;
    }

    public void setCodEmprestimo(int codEmprestimo) {
        this.codEmprestimo = codEmprestimo;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        return this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    
    public int getRenovacoes() {
        return renovacoes;
    }

    public void setRenovacoes(int renovacoes) {
        this.renovacoes = renovacoes;
    }
    
    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public LocalDate getDataDevolucaoEfetiva() {
        return dataDevolucaoEfetiva;
    }

    public void setDataDevolucaoEfetiva(LocalDate dataDevolucaoEfetiva) {
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
    }
}
