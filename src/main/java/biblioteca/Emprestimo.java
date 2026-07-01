package main.java.biblioteca;

import java.time.LocalDate;


public class Emprestimo {
    private static int cont = 0;

    private int id;
    private Livro livro;
    private String nomeCliente;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, String nomeCliente){
        this.id = ++cont;
        this.livro = livro;
        this.nomeCliente = nomeCliente;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = null;
    }

    public void devolveLivro(){
        if(livro.getStatus() == LivroEnum.EMPRESTADO){
            this.dataDevolucao = LocalDate.now();
            this.livro.devolveLivro();
        }
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
}
