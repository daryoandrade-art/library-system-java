package biblioteca.repository;

import biblioteca.db.ConexaoDB;
import biblioteca.model.Emprestimo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    public Emprestimo emprestar(Emprestimo emprestimo) throws SQLException {
        String sqlInsert = "INSERT INTO emprestimo (nome_cliente, livro_id)  VALUES (?, ?) RETURNING id";
        String sqlUpdate = "UPDATE livro SET status = 'INDISPONIVEL' WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
            stmt.setInt(1, emprestimo.getIdLivro());
            stmt.executeUpdate();
        }
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {
            stmt.setString(1, emprestimo.getNomeCliente());
            stmt.setInt(2, emprestimo.getIdLivro());
            var rs = stmt.executeQuery();
            if (rs.next()) {
                emprestimo.setId(rs.getInt("id"));
            }
        }
        return emprestimo;
    }

    public Emprestimo devolver(Emprestimo emprestimo) throws SQLException {
        String sqlUpdate = "UPDATE livro SET status = 'DISPONIVEL' WHERE id = ?";
        String sqlUpdate2 = "UPDATE emprestimo SET data_devolucao = CURRENT_DATE WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {
            stmt.setInt(1, emprestimo.getIdLivro());
            stmt.executeUpdate();
        }
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlUpdate2)) {
            stmt.setInt(1, emprestimo.getId());
            stmt.executeUpdate();
        }
        return emprestimo;
    }

    public List<Emprestimo> listarTodos() throws SQLException {
        String sql = "SELECT * FROM emprestimo";
        List<Emprestimo> emprestimos = new ArrayList<>();
        try (Connection conn = ConexaoDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("id"),
                        rs.getString("nome_cliente"),
                        rs.getInt("livro_id"),
                        rs.getDate("data_emprestimo").toLocalDate(),
                        rs.getDate("data_devolucao") != null ? rs.getDate("data_devolucao").toLocalDate() : null
                );
                emprestimos.add(emprestimo);
            }
        }
        return emprestimos;
    }

    public Emprestimo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM emprestimo WHERE id = ?";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Emprestimo(
                        rs.getInt("id"),
                        rs.getString("nome_cliente"),
                        rs.getInt("livro_id"),
                        rs.getDate("data_emprestimo").toLocalDate(),
                        rs.getDate("data_devolucao") != null ? rs.getDate("data_devolucao").toLocalDate() : null
                );
            }
        }
        return null;
    }

}