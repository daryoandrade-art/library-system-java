package biblioteca.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDB {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/library_system_db";
        Properties props = new Properties();
        props.setProperty("user", System.getenv("DB_USER"));
        props.setProperty("password", System.getenv("DB_PASSWORD"));
        return DriverManager.getConnection(url, props);
    }
}
