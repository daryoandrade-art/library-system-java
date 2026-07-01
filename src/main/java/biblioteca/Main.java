package main.java.biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Autor autor1 = new Autor("J.K. Rowling", LocalDate.of(1965, 7, 31));
        Autor autor2 = new Autor("George R.R. Martin", LocalDate.of(1948, 9, 20));
        Autor autor3 = new Autor("Machado de Assis", LocalDate.of(1839, 6, 21));
        Autor autor4 = new Autor("Clarice Lispector", LocalDate.of(1920, 12, 10));
        Autor autor5 = new Autor("J. R. R. Tolkien", LocalDate.of(1892, 1, 3));

        biblioteca.adicionaAutor(autor1);
        biblioteca.adicionaAutor(autor2);
        biblioteca.adicionaAutor(autor3);
        biblioteca.adicionaAutor(autor4);
        biblioteca.adicionaAutor(autor5);

        Livro livro1 = new Livro("Harry Potter e a Pedra Filosofal", autor1);
        Livro livro2 = new Livro("Harry Potter e a Câmara Secreta", autor1);
        Livro livro3 = new Livro("A Game of Thrones", autor2);
        Livro livro4 = new Livro("A Clash of Kings", autor2);
        Livro livro5 = new Livro("Dom Casmurro", autor3);
        Livro livro6 = new Livro("Memórias Póstumas de Brás Cubas", autor3);
        Livro livro7 = new Livro("A Hora da Estrela", autor4);
        Livro livro8 = new Livro("Perto do Coração Selvagem", autor4);
        Livro livro9 = new Livro("O Senhor dos Anéis: A Sociedade do Anel", autor5);
        Livro livro10 = new Livro("O Senhor dos Anéis: As Duas Torres", autor5);

        biblioteca.adicionaLivro(livro1);
        biblioteca.adicionaLivro(livro2);
        biblioteca.adicionaLivro(livro3);
        biblioteca.adicionaLivro(livro4);
        biblioteca.adicionaLivro(livro5);
        biblioteca.adicionaLivro(livro6);
        biblioteca.adicionaLivro(livro7);
        biblioteca.adicionaLivro(livro8);
        biblioteca.adicionaLivro(livro9);
        biblioteca.adicionaLivro(livro10);

        Emprestimo emp1 = new Emprestimo(livro3, "Daryo S. Andrade");
        biblioteca.adicionaEmprestimo(emp1);

        Emprestimo emp2 = new Emprestimo(livro6, "Daryo S. Andrade");
        biblioteca.adicionaEmprestimo(emp2);

        menuPrincipal(biblioteca);
    }

    public static void menuPrincipal(Biblioteca biblioteca) {
        Scanner scan = new Scanner(System.in);
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
                case 1: emprestarLivro(biblioteca);
                    break;
                case 2: devolverLivro(biblioteca);
                    break;
                case 3: menuCadastro(biblioteca);
                    break;
                case 9: System.out.println("Saindo...");
                    scan.close();
                    break;
                default: System.out.println("Opção inválida");
                    menuPrincipal(biblioteca);
                    break;
            }
        } catch (Exception e){
            System.out.println("Opção inválida");
            menuPrincipal(biblioteca);
        }
    }

    public static void emprestarLivro(Biblioteca biblioteca){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n--- LIVROS DISPONÍVEIS ---");
        for (Livro livro : biblioteca.ListarLivrosDisponiveis()) {
            System.out.printf("ID: %d |  Titulo: %s\n", livro.getId(), livro.getTitulo());
        }
        System.out.print("Digite o ID do livro que deseja emprestar: ");

        if(!scan.hasNextInt()){
            System.out.println("Entrada invalida! Digite apenas números");
            scan.nextLine(); //limpa o scan
            return;
        }

        int id = scan.nextInt();
        scan.nextLine(); //limpa o scan
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
        Emprestimo emprestimo = biblioteca.registraEmprestimo(livro, nomeCliente);
        System.out.println("\nEmprestimo registrado com sucesso!");
        menuPrincipal(biblioteca);

    }

    public static void devolverLivro(Biblioteca biblioteca){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n--- LIVROS EMPRESTADOS ---");
        for (Emprestimo emprestimo : biblioteca.listarEmprestimos()) {
            System.out.printf("ID: %d |  Titulo: %s | Cliente: %s\n", emprestimo.getId(), emprestimo.getLivro().getTitulo(), emprestimo.getNomeCliente());
        }
        System.out.print("Digite o ID do empréstimo a devolver: ");
        if(!scan.hasNextInt()){
            System.out.println("Entrada invalida! Digite apenas números");
            scan.nextLine(); //limpa o scan
            return;
        }
        int id = scan.nextInt();
        scan.nextLine(); //limpa o scan
        Emprestimo emprestimo = biblioteca.buscarEmprestimoPorId(id);

        if (emprestimo == null) {
            System.out.println("Empréstimo não encontrado!");
            return;
        }
        if (emprestimo.getLivro().getStatus() == LivroEnum.DISPONIVEL) {
            System.out.println("Livro já devolvido!");
            return;
        }
        emprestimo.devolveLivro();
        menuPrincipal(biblioteca);
    }

    public static void menuCadastro(Biblioteca biblioteca){
        Scanner scan = new Scanner(System.in);
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
                    System.out.println("Digite o nome do autor: ");
                    String nomeAutor = scan.nextLine();
                    System.out.println("Digite a data de nascimento do autor (DD-MM-YYY): ");
                    String dataNascimentoStr = scan.nextLine();
                    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, formatador);
                    biblioteca.cadastrarAutor(nomeAutor, dataNascimento);
                    System.out.println("Autor cadastrado com sucesso!");
                    menuPrincipal(biblioteca);
                    break;
                case 2:
                    System.out.println("Cadastro de Livro");
                    System.out.println("Para cadastro de livro selecione um autor dos autores cadastrados:");
                    for (Autor autor : biblioteca.listarAutores()) {
                        System.out.printf("ID: %d | Nome: %s\n", autor.getId(), autor.getNome());
                    }
                    System.out.print("Escolha uma opcão: ");
                    if(!scan.hasNextInt()){
                        System.out.println("Entrada invalida! Digite apenas números");
                        scan.nextLine(); //limpa o scan
                        return;
                    }
                    int id = scan.nextInt();
                    scan.nextLine(); //limpa o scan
                    Autor autor = biblioteca.buscarAutorPorId(id);
                    if (autor == null) {
                        System.out.println("Autor não encontrado!");
                        return;
                    }
                    String autorNome = autor.getNome();
                    System.out.println("Digite o nome do livro");
                    String titulo = scan.nextLine();
                    biblioteca.cadastrarLivro(titulo, autor);
                    System.out.println("Livro cadastrado com sucesso!");
                    menuPrincipal(biblioteca);
                    break;
                case 3:
                    System.out.println("Voltando ao menu principal...");
                    menuPrincipal(biblioteca);
                    break;
                default:
                    System.out.println("Opção inválida");
                    menuCadastro(biblioteca);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Opção inválida");
            menuCadastro(biblioteca);
        }
    }
}
