package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop = new Properties();

    static {
        String env = System.getProperty("env", "stage");

        try {
            InputStream is = ConfigReader.class.getClassLoader()
                    .getResourceAsStream("config-" + env + ".properties");

            prop.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Config file not found");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(prop.getProperty(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(prop.getProperty(key));
    }
}
