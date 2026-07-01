package main.java.biblioteca;

import java.time.LocalDateTime;

public class Livro {
    private static int cont = 0;

    private int id;
    private String titulo;
    private Autor autor;
    private LivroEnum status;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;

    public Livro(String titulo, Autor autor, LivroEnum status){
        this.id = ++cont;
        this.titulo = titulo;
        this.autor = autor;
        this.status = status;
        this.dataCadastro = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void emprestaLivro(){
        this.status = LivroEnum.EMPRESTADO;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public void devolveLivro(){
        this.status = LivroEnum.DISPONIVEL;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public LivroEnum getStatus() {
        return status;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public int getId() {
        return id;
    }
}
