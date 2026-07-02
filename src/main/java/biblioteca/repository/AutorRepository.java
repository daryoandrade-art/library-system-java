package biblioteca.repository;

import biblioteca.db.ConexaoDB;
import biblioteca.model.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorRepository {

    public Autor salvar(Autor autor) throws SQLException{
        String sql = "INSERT INTO autor (nome, data_nascimento) VALUES (?, ?) RETURNING id";
        try (Connection conn = ConexaoDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setDate(2, java.sql.Date.valueOf(autor.getDataNascimento()));
            var rs = stmt.executeQuery();
            if (rs.next()) {
                autor.setId(rs.getInt("id"));
            }
        }
        return autor;
    }

    public List<Autor> listarTodos() throws SQLException {
        String sql = "SELECT * FROM autor";
        List<Autor> autores = new ArrayList<>();
        try(Connection conn = ConexaoDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
                while (rs.next()) {
                    Autor autor = new Autor(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data_nascimento").toLocalDate()
                    );
                    autores.add(autor);
                }
        }
        return autores;
    }

    public Autor buscarPorId(int id) throws SQLException{
        String sql = "SELECT * FROM autor WHERE id = ?";
        List<Autor> autores = new ArrayList<>();
        try(Connection conn = ConexaoDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Autor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDate("data_nascimento").toLocalDate()
                );
            }
        }
        return null;
    }
}
