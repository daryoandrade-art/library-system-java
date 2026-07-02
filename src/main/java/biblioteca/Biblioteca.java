package biblioteca;

import biblioteca.model.Autor;
import biblioteca.model.Emprestimo;
import biblioteca.model.Livro;
import biblioteca.model.LivroEnum;
import biblioteca.repository.AutorRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private final AutorRepository autorRepository = new AutorRepository();

    public void adicionaLivro(Livro livro) {
        this.livros.add(livro);
    }

    public void adicionaEmprestimo(Emprestimo emprestimo){
        this.emprestimos.add(emprestimo);
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

    public void cadastrarLivro(String titulo, Autor autor){
        Livro livro = new Livro(titulo, autor);
        this.livros.add(livro);
    }

    public Autor cadastrarAutor(String nome, LocalDate dataNascimento) throws SQLException{
        Autor autor = new Autor(nome, dataNascimento);
        return autorRepository.salvar(autor);
    }

    public List<Autor> listarAutores() throws SQLException{
        return autorRepository.listarTodos();
    }

    public Autor buscarAutorPorId(int id) throws SQLException{
        return autorRepository.buscarPorId(id);
    }
}

