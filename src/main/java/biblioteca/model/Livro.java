package biblioteca.model;

import java.time.LocalDate;


public class Livro {
    private int id;
    private String titulo;
    private int idAutor;
    private LivroEnum status;
    private LocalDate dataCadastro;
    private LocalDate dataAtualizacao;



    public Livro(String titulo, int idAutor){
        this.titulo = titulo;
        this.idAutor = idAutor;
    }

    public Livro(int id, String titulo, int idAutor, String status, LocalDate dataCadastro, LocalDate dataAtualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.status = LivroEnum.valueOf(status);
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LivroEnum getStatus() {
        return status;
    }

    public void setStatus(LivroEnum status) {
        this.status = status;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }
}