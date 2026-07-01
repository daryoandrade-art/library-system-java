package main.java.biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();

    public void adicionaLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void adicionaEmprestimo(Emprestimo emprestimo){

        this.emprestimos.add(emprestimo);
    }

    public void adicionaAutor(Autor autor){
        this.autores.add(autor);
    }

    public List<Livro> ListarLivrosDisponiveis(){
        List<Livro> livrosDisponiveis = new ArrayList<>();
        for (Livro livro : livros){
            if(livro.getStatus() == LivroEnum.DISPONIVEL){
                livrosDisponiveis.add(livro);
            }
        }
        return livrosDisponiveis;
    }

    public List<Emprestimo> listarEmprestimos(){
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos){
            if(emprestimo.getDataDevolucao() == null){
                emprestimosAtivos.add(emprestimo);
            }
        }
        return emprestimosAtivos;
    }

    public List<Autor> listarAutores(){
        return this.autores;
    }

    public Autor buscarAutorPorId(int id){
        for (Autor autor : autores){
            if(autor.getId() == id){
                return autor;
            }
        }
        return null;
    }

    public Emprestimo buscarEmprestimoPorId(int id){
        for (Emprestimo emprestimo : emprestimos){
            if(emprestimo.getId() == id){
                return emprestimo;
            }
        }
        return null;
    }

    public Livro buscarLivroPorId(int id){
        for (Livro livro : livros){
            if(livro.getId() == id){
                return livro;
            }
        }
        return null;
    }

    public Emprestimo registraEmprestimo(Livro livro, String nomeCliente){
        Emprestimo emprestimo = new Emprestimo(livro, nomeCliente);
        emprestimos.add(emprestimo);
        livro.emprestaLivro();
        return emprestimo;
    }

    public void cadastrarAutor(String nomeAutor, LocalDate dataNasciment){
        Autor autor = new Autor(nomeAutor, dataNasciment);
        this.autores.add(autor);
    }

    public void cadastrarLivro(String titulo, Autor autor){
        Livro livro = new Livro(titulo, autor);
        this.livros.add(livro);
    }

}

