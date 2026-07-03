package biblioteca;

import biblioteca.db.EnvConfig;
import biblioteca.model.Autor;
import biblioteca.model.Emprestimo;
import biblioteca.model.Livro;
import biblioteca.model.LivroEnum;
import org.flywaydb.core.Flyway;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {
        Flyway flyway = Flyway.configure()
                .dataSource(EnvConfig.get("DB_URL"), EnvConfig.get("DB_USER"), EnvConfig.get("DB_PASSWORD"))
                .load();
        flyway.migrate();

        Scanner scan = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        menuPrincipal(biblioteca, scan);
        scan.close();
    }

    public static void menuPrincipal(Biblioteca biblioteca, Scanner scan) {
        System.out.println("Bem vindo à sua biblioteca");
        System.out.println("O que precisa para hoje?");
        System.out.println("1 - Emprestar livro");
        System.out.println("2 - Devolver livro");
        System.out.println("3 - Cadastros");
        System.out.println("9 - Sair");
        try{
            System.out.print("Escolha uma opção: ");
            int opcao = scan.nextInt();
            switch (opcao){
                case 1: emprestarLivro(biblioteca, scan);
                    break;
                case 2: devolverLivro(biblioteca, scan);
                    break;
                case 3: menuCadastro(biblioteca, scan);
                    break;
                case 9: System.out.println("Saindo...");
                    break;
                default: System.out.println("Opção inválida");
                    menuPrincipal(biblioteca, scan);
                    break;
            }
        } catch (Exception e){
            System.out.println("Opção inválida");
            menuPrincipal(biblioteca, scan);
        }
    }

    public static void emprestarLivro(Biblioteca biblioteca, Scanner scan) throws SQLException {
        System.out.println("\n--- LIVROS DISPONÍVEIS ---");
        for (Livro livro : biblioteca.ListarLivrosDisponiveis()) {
            System.out.printf("%02d |  Titulo: %s\n", livro.getId(), livro.getTitulo());
        }
        System.out.println("Digite 0 para retornar ao menu principal");
        System.out.print("Digite o numero do livro que deseja emprestar: ");

        if(!scan.hasNextInt()){
            System.out.println("Entrada invalida! Digite apenas números");
            scan.nextLine(); //limpa o scan
            return;
        }

        int id = scan.nextInt();
        scan.nextLine(); //limpa o scan
        if (id == 0){
            menuPrincipal(biblioteca, scan);
            return;
        }
        Livro livro = biblioteca.buscarLivroPorId(id);

        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }
        if (livro.getStatus() == LivroEnum.INDISPONIVEL) {
            System.out.println("Livro indisponível para empréstimo!");
            return;
        }

        System.out.print("Digite o nome do responsavel pelo emprestimo: ");
        String nomeCliente = scan.nextLine();
        Emprestimo emprestimo = biblioteca.registraEmprestimo(nomeCliente, id);
        System.out.println("\nEmpréstimo registrado com sucesso!");
        menuPrincipal(biblioteca, scan);

    }

    public static void devolverLivro(Biblioteca biblioteca, Scanner scan) throws SQLException {
        System.out.println("\n--- LIVROS EMPRESTADOS ---");
        for (Emprestimo emprestimo : biblioteca.listarEmprestimos()) {
            Livro livro = biblioteca.buscarLivroPorId(emprestimo.getIdLivro());
            String nomeLivro = livro.getTitulo();
            System.out.printf("%02d |  Titulo: %s | Cliente: %s\n", emprestimo.getId(),nomeLivro, emprestimo.getNomeCliente());
        }
        System.out.println("Digite 0 para retornar ao menu principal");
        System.out.print("Digite o numero do empréstimo a devolver: ");
        if(!scan.hasNextInt()){
            System.out.println("Entrada invalida! Digite apenas números");
            scan.nextLine(); //limpa o scan
            return;
        }
        int id = scan.nextInt();
        Emprestimo emprestimo = biblioteca.buscarEmprestimoPorId(id);
        scan.nextLine(); //limpa o scan
        if (id == 0){
            menuPrincipal(biblioteca, scan);
            return;
        }

        if (emprestimo == null) {
            System.out.println("Empréstimo não encontrado!");
            return;
        }
        Livro livro = biblioteca.buscarLivroPorId(emprestimo.getIdLivro());
        if (livro.getStatus() == LivroEnum.DISPONIVEL) {
            System.out.println("Livro já devolvido!");
            return;
        }
        biblioteca.registraDevolucao(emprestimo);
        System.out.println("\nDevolução registrada com sucesso!");
        menuPrincipal(biblioteca, scan);
    }

    public static void menuCadastro(Biblioteca biblioteca, Scanner scan){
        System.out.println("Menu Cadastro");
        System.out.println("1 - Cadastro Autor");
        System.out.println("2 - Cadastro Livro");
        System.out.println("3 - Voltar ao menu principal");
        try {
            System.out.print("Escolha uma opção: ");
            int opcao = scan.nextInt();
            scan.nextLine(); //limpa o scan
            switch (opcao) {
                case 1:
                    System.out.println("Cadastro de Autor");
                    System.out.println("Digite o nome do autor: (sair para cancelar)");
                    String nomeAutor = scan.nextLine();
                    if (nomeAutor.equalsIgnoreCase("sair")) {
                        menuCadastro(biblioteca, scan);
                        return;
                    }
                    System.out.println("Digite a data de nascimento do autor (DD-MM-YYY): ");
                    String dataNascimentoStr = scan.nextLine();
                    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatador);
                    biblioteca.cadastrarAutor(nomeAutor, dataNascimento);
                    System.out.println("Autor cadastrado com sucesso!");
                    menuPrincipal(biblioteca, scan);
                    break;
                case 2:
                    System.out.println("Cadastro de Livro");
                    System.out.println("Para cadastro de livro selecione um autor dos autores cadastrados:");
                    for (Autor autor : biblioteca.listarAutores()) {
                        System.out.printf("%02d | Nome: %s\n", autor.getId(), autor.getNome());
                    }
                    System.out.println("Digite 0 para retornar ao menu principal");
                    System.out.print("Escolha uma opcão: ");
                    if(!scan.hasNextInt()){
                        System.out.println("Entrada invalida! Digite apenas números");
                        scan.nextLine(); //limpa o scan
                        return;
                    }
                    int id = scan.nextInt();
                    scan.nextLine(); //limpa o scan
                    if (id == 0){
                        menuPrincipal(biblioteca, scan);
                        return;
                    }
                    Autor autor = biblioteca.buscarAutorPorId(id);
                    if (autor == null) {
                        System.out.println("Autor não encontrado!");
                        return;
                    }
                    System.out.println("Digite o nome do livro");
                    String titulo = scan.nextLine();
                    biblioteca.cadastrarLivro(titulo, autor.getId());
                    System.out.println("Livro cadastrado com sucesso!");
                    menuPrincipal(biblioteca, scan);
                    break;
                case 3:
                    System.out.println("Voltando ao menu principal...");
                    menuPrincipal(biblioteca, scan);
                    break;
                default:
                    System.out.println("Opção inválida");
                    menuCadastro(biblioteca, scan);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Opção inválida");
            menuCadastro(biblioteca, scan);
        }
    }
}
