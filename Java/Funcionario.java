package Java;

import java.io.Serializable;

public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private float salario;
    private int codigo;

    private static int contador = 1001;

    public static void setContador(int valor) {
        contador = valor;
    }

    public Funcionario(String nome, float salario) {
        this.nome = nome;
        this.salario = salario;
        this.codigo = contador;
        contador++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public int getCodigo() {
        return codigo;
    }
}
