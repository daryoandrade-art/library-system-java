package biblioteca.db;

import biblioteca.db.EnvConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDB {
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", EnvConfig.get("DB_USER"));
        props.setProperty("password", EnvConfig.get("DB_PASSWORD"));
        return DriverManager.getConnection(EnvConfig.get("DB_URL"), props);
    }
}