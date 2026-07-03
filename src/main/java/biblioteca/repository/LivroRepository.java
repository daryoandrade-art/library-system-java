package biblioteca.repository;

import biblioteca.db.ConexaoDB;
import biblioteca.model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    public Livro salvar(Livro livro) throws SQLException {
        String sql = "INSERT INTO livro (titulo, autor_id) VALUES (?, ?) RETURNING id";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getIdAutor());
            var rs = stmt.executeQuery();
            if (rs.next()) {
                livro.setId(rs.getInt("id"));
            }
        }
        return livro;
    }

    public List<Livro> listarTodos() throws SQLException {
        String sql = "SELECT * FROM livro";
        List<Livro> livros = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("autor_id"),
                        rs.getString("status"),
                        rs.getDate("data_cadastro").toLocalDate(),
                        rs.getDate("data_atualizacao") != null ? rs.getDate("data_atualizacao").toLocalDate() : null
                );
                livros.add(livro);
            }
        }
        return livros;
    }

    public Livro buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM livro WHERE id = ?";
        List<Livro> livros = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("autor_id"),
                        rs.getString("status"),
                        rs.getDate("data_cadastro").toLocalDate(),
                        rs.getDate("data_atualizacao") != null ? rs.getDate("data_atualizacao").toLocalDate() : null
                );
            }
        }
        return null;
    }

    public List<Livro> listarDisponiveis() throws SQLException {
        String sql = "SELECT * FROM livro WHERE status = 'DISPONIVEL'";
        List<Livro> livros = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("autor_id"),
                        rs.getString("status"),
                        rs.getDate("data_cadastro").toLocalDate(),
                        rs.getDate("data_atualizacao") != null ? rs.getDate("data_atualizacao").toLocalDate() : null
                );
                livros.add(livro);
            }
        }
        return livros;
    }


}