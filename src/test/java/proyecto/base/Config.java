package proyecto.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            PROPS.load(in);
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("No se pudo leer config.properties", e);
        }
    }

    private Config() {}

    public static String get(String key) {
        return System.getProperty(key, PROPS.getProperty(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}