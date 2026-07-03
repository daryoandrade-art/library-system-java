package biblioteca;

import biblioteca.model.Autor;
import biblioteca.model.Emprestimo;
import biblioteca.model.Livro;
import biblioteca.model.LivroEnum;
import biblioteca.repository.AutorRepository;
import biblioteca.repository.EmprestimoRepository;
import biblioteca.repository.LivroRepository;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private final EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
    private final LivroRepository livroRepository = new LivroRepository();
    private final AutorRepository autorRepository = new AutorRepository();

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

    public Livro cadastrarLivro(String titulo, int idAutor) throws SQLException{
        Livro livro = new Livro(titulo, idAutor);
        return livroRepository.salvar(livro);
    }

    public List<Livro> listarLivros() throws SQLException{
        return livroRepository.listarTodos();
    }

    public Livro buscarLivroPorId(int id) throws SQLException{
        return livroRepository.buscarPorId(id);
    }

    public List<Livro> ListarLivrosDisponiveis() throws SQLException{
        return livroRepository.listarDisponiveis();
    }

    public Emprestimo registraEmprestimo(String nomeCliente, int idLivro) throws SQLException{
        Emprestimo emprestimo = new Emprestimo(nomeCliente, idLivro);
        return emprestimoRepository.emprestar(emprestimo);
    }

    public Emprestimo registraDevolucao(Emprestimo emprestimo) throws SQLException{
        return emprestimoRepository.devolver(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos() throws SQLException{
        return emprestimoRepository.listarTodos();
    }

    public List<Emprestimo> listarEmprestimosPendentes() throws SQLException{
        return emprestimoRepository.listarPendentes();
    }

    public Emprestimo buscarEmprestimoPorId(int id) throws SQLException {
        return emprestimoRepository.buscarPorId(id);
    }


}

