package biblioteca.db;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.load();

    public static String get(String key) {
        String value = dotenv.get(key);
        if (value == null) {
            throw new IllegalStateException("Variável " + key + " não encontrada no .env");
        }
        return value;
    }
}

