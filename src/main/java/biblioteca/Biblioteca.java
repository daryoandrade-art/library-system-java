package main.java.biblioteca;

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
}