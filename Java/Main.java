package Java;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Main {
    static Scanner teclado = new Scanner(System.in);
    static CadastroFunc listaFuncionarios = new CadastroFunc(); // Lista dos funcionários
    static CadastroLivros acervo = new CadastroLivros(); //Lista de todos os livros da biblioteca
    static CadastroClientes clientes = new CadastroClientes(); //Lista de todos os clientes
    static CadastroEmprestimos emprestimos = new CadastroEmprestimos(); // Lista de todos os empréstimos
    static Menus menus = new Menus();
    public static void main(String[] args) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"); // Limpar terminal
        menu();
        teclado.close();
    }

    public static void menu() {
        Menus menu = new Menus();
        while (true) {
            int opcao = lerInteiro(menu.getMenuPrincipal());
            if (opcao == 0) {
                break;
            }
            switch (opcao) {
                case 1 -> {
                    menuFuncionarios();
                }

                case 2 -> {
                    menuLivros();
                }

                case 3 -> {
                    menuClientes();
                }

                case 4 -> {
                    menuEmprestimos();
                }              

                default -> {
                    imprimirMensagemSaltada("Opção inválida!");
                    break;
                }
            }
        }
    }

    public static void menuFuncionarios () {
        Menus menu = new Menus();
        while (true) {
            int opcao = lerInteiro(menu.getMenuFuncionarios());
            if (opcao == 0) {
                break;
            }
            switch (opcao) {
                case 1 -> {
                    Funcionario funcionario = lerFuncionario();
                    listaFuncionarios.cadastrarFuncionario(funcionario);
                    imprimirMensagemSaltada("Funcionário cadastrado com sucesso!");
                    break;
                }

                case 2 -> {
                    listaFuncionarios.listarFuncionarios();
                    break;
                }

                case 3 -> {
                    int codigo = lerInteiro("Digite o código do funcionário que deseja buscar");
                    Funcionario funcionario = descobrirFuncionario(codigo);
                    listaFuncionarios.imprimirFuncionario(funcionario);
                    break;
                }

                case 4 -> {
                    int codigo = lerInteiro("Digite o código do funcionário que deseja remover");
                    listaFuncionarios.removerFuncionario(codigo);
                    break;
                }

                default -> {
                    imprimirMensagemSaltada("Opção inválida!");
                    break;
                }
            }
        }
    }

    public static void menuLivros() {
        Menus menu = new Menus();
        while (true) {
            int opcao = lerInteiro(menu.getMenuLivros());
            if (opcao == 0) {
                break;
            }
            switch (opcao) {
                case 1 -> {
                    Livro livro = lerLivro();
                    acervo.cadastrarLivros(livro);
                    imprimirMensagemSaltada("Livro cadastrado com sucesso!");
                    break;
                }

                case 2 -> {
                    acervo.listarLivros("Livros cadastrados");
                    break;
                }

                case 3 -> {
                    int codigo = lerInteiro("Digite o código do livro que deseja buscar");
                    Livro livro = descobrirLivroPorCodigo(codigo);
                    livro.imprimirLivro(livro);
                    break;
                }

                case 4 -> {
                    CadastroLivros encontrados = new CadastroLivros();
                    encontrados = descobrirLivroPorTitulo();
                    if (encontrados == null) {
                        imprimirMensagemSaltada("Nenhum livro encontrado com essa palavra no título");
                        break;
                    } else {
                    encontrados.listarLivros("Livros encontrados: ");
                    }
                    break;
                }

                case 5 -> {
                    int codigo = lerInteiro("Digite o código do livro que deseja remover");
                    acervo.removerLivro(codigo);
                    break;
                }

                default -> {
                    imprimirMensagemSaltada("Opção inválida!");
                    break;
                }
            }
            
        }
    }

    public static void menuClientes() {
        Menus menu = new Menus();
        while (true) {
            int opcao = lerInteiro(menu.getMenuClientes());
            if (opcao == 0) {
                break;
            }
            switch (opcao) {
                case 1 -> {
                    Cliente cliente = lerCliente();
                    clientes.cadastrarCliente(cliente);
                    imprimirMensagemSaltada("Cliente cadastrado com sucesso!");
                    break;
                }

                case 2 -> {
                    clientes.listarClientes();
                    break;
                }

                case 3 -> {
                    int codigo = lerInteiro("Digite o código do cliente que deseja buscar");
                    Cliente cliente = clientes.buscarCliente(codigo);
                    if (cliente == null) {
                        imprimirMensagemSaltada("Nenhum cliente encontrado com esse código");
                        break;
                    } else {
                        cliente.imprimirCliente(cliente);
                    }
                    break;
                }

                case 4 -> {
                    int codigoCliente = lerInteiro("Digite o código do cliente que deseja buscar os empréstimos em aberto");
                    CadastroEmprestimos encontrados = new CadastroEmprestimos();
                    encontrados = emprestimos.buscarEmprestimo(codigoCliente);
                    if (encontrados == null) {
                        imprimirMensagemSaltada("Nenhum empréstimo em aberto encontrado para esse cliente");
                        break;
                    } else {
                        encontrados.listarEmprestimos("Empréstimos em aberto para o cliente de código " + codigoCliente);
                    }
                    break;
                }

                case 5 -> {
                    int codigo = lerInteiro("Digite o código do cliente que deseja remover");
                    clientes.removerCliente(codigo);
                    break;
                }

                default -> {
                    imprimirMensagemSaltada("Opção inválida!");
                    break;
                }
            }
        }
    }

    public static void menuEmprestimos() {
        Menus menu = new Menus();
        while (true) {
            int opcao = lerInteiro(menu.getMenuEmprestimos());

            if (opcao == 0) {
                break;
            }

            switch (opcao) {
                case 1 -> {
                    emprestimos.listarEmprestimos("Empréstimos em aberto");
                    break;
                }

                case 2 -> {
                    Emprestimo emprestimo = lerEmprestimo();
                    emprestimos.realizarEmprestimos(emprestimo);
                    break;
                    }
            
                case 3 -> {
                    int codigoEmprestimo = lerInteiro("Digite o código do empréstimo que deseja devolver");
                    emprestimos.devolverLivro(codigoEmprestimo);
                    break;
                }

                case 4 -> {
                    int codigoEmprestimo = lerInteiro("Digite o código do empréstimo que deseja renovar");
                    emprestimos.renovarEmprestimo(codigoEmprestimo);
                    break;
                }
            
                case 5 -> {
                    int codigoEmprestimo = lerInteiro("Digite o código do empréstimo que deseja especificar");
                    Emprestimo emprestimo = emprestimos.buscarEmprestimoPorCodigo(codigoEmprestimo);
                    if (emprestimo == null) {
                        imprimirMensagemSaltada("Nenhum empréstimo encontrado com esse código");
                        break;
                    } else {
                        emprestimo.imprimirEmprestimo(emprestimo);
                    }
                    break;
                }
        
                case 6 -> {
                    int codigoCliente = lerInteiro("Digite o código do cliente que deseja buscar os empréstimos em aberto");
                    CadastroEmprestimos encontrados = new CadastroEmprestimos();
                    encontrados = emprestimos.buscarEmprestimo(codigoCliente);
                    if (encontrados == null) {
                        imprimirMensagemSaltada("Nenhum empréstimo em aberto encontrado para esse cliente");
                        break;
                    } else {
                        encontrados.listarEmprestimos("Empréstimos em aberto para o cliente de código " + codigoCliente);
                    }
                    break;
                }
    
                case 7 -> {
                    int codigoEmprestimo = lerInteiro("Digite o código do empréstimo que deseja remover");
                    emprestimos.removerEmprestimo(codigoEmprestimo);
                    break;
                }

                default -> {
                    imprimirMensagemSaltada("Opção inválida!");
                    break;
                }
            }
        }
    }

    public static Emprestimo lerEmprestimo() {
        int codigoCliente = lerInteiro("Digite o código do cliente que está realizando o empréstimo");
        Cliente cliente = clientes.buscarCliente(codigoCliente);
        int codigoFuncionario = lerInteiro("Digite o código do funcionário que está realizando o empréstimo");
        Funcionario funcionario = descobrirFuncionario(codigoFuncionario);
        int codigoLivro = lerInteiro("Digite o código do livro que deseja emprestar");
        Livro livro = descobrirLivroPorCodigo(codigoLivro);
        Emprestimo emprestimo = new Emprestimo(livro, cliente, funcionario);
        return emprestimo;
    }

    public static Cliente lerCliente() {
        String nome = lerString("Digite o nome do cliente");
        String telefone = lerString("Digite o telefone do cliente");
        String cpf = lerString("Digite o CPF do cliente");
        Cliente cliente = new Cliente(nome, telefone, cpf);
        return cliente;
    }

    public static CadastroLivros descobrirLivroPorTitulo() {
    String palavra = lerString("Digite uma palavra para buscar no título do livro:");
    CadastroLivros encontrados = acervo.buscarLivrosPorTitulo(palavra);
    return encontrados;
    }

    public static Livro descobrirLivroPorCodigo(int codigo) { // ? Função que lê o código do livro que deseja buscar e retorna o livro respectivo
        Livro livro = acervo.buscarLivros(codigo);
        return livro;
    }

    public static Livro lerLivro() { // ? Função que lê os dados do livro para poder criar um objeto livro e retorna o mesmo
        String nome = lerString("Digite o nome do livro");
        int exemplares = lerInteiro("Digite a quantidade de exemplares desse livro");
        int emprestados = 0; // Como é um cadastro de um livro, não tem como ter sido feito um empréstimo, então começa com 0
        int ano = lerInteiro("Digite o ano de publicação desse livro");
        String autor = lerString("Digite o autor desse livro");
        Livro livro = new Livro(nome, exemplares, emprestados, ano, autor);
        return livro;
    }

    public static Funcionario descobrirFuncionario(int codigo) { // ? Função que lê o código do funcionário que deseja buscar e retorna o funcionário respectivo
        Funcionario funcionario = listaFuncionarios.buscarFuncionario(codigo);
        return funcionario;
    }

    public static Funcionario lerFuncionario() { // ? Função que lê os dados do funcionário para poder criar um objeto funcionário
        String nome = lerString("Digite o nome do funcionário");
        float salario = lerFloat("Digite o salário desse funcionário");
        // teclado.nextLine(); // limpar buffer
        Funcionario funcionario = new Funcionario(nome, salario);
        return funcionario;
    }

    public static int lerInteiro(String pergunta) { // ? Onde saltará uma caixa de pergunta na tela, oq for digitado
        // será convertido e retornado no modelo inteiro
        String resposta = JOptionPane.showInputDialog(pergunta).trim();
        int n = Integer.parseInt(resposta);
        return n;
    }

    public static float lerFloat(String pergunta) { // ? Onde saltará uma caixa de pergunta na tela, oq for digitado
        // será convertido e retornado no modelo inteiro
        String resposta = JOptionPane.showInputDialog(pergunta).trim();
        
        float n = Float.parseFloat(resposta);
        return n;
    }

    public static String lerString(String pergunta) { // ? Onde saltará uma caixa de pergunta na tela, oq for digitado
        // será retornado no modelo string
        String n = JOptionPane.showInputDialog(pergunta);
        return n;
    }

    public static void imprimirMensagemSaltada(String mensagem) { // ? Função que mostra cada mensagem saltada na tela,
        // fora do terminal
        JOptionPane.showMessageDialog(null, mensagem);
    }

}
