package main.java.biblioteca;

import java.time.LocalDate;

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
    }
}
