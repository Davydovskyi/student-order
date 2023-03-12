package edu.jcourse.student_order.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_LIMIT = "db.limit";


    private static final Properties properties = new Properties();

    private Config() {
    }

    //TODO refactoring 'throw new RuntimeException(e)'
    public static String getProperties(String name) {
        if (!properties.isEmpty()) {
            return properties.getProperty(name);
        }
        synchronized (Config.class) {
            try (InputStream is = Config.class.getClassLoader().getResourceAsStream("dao.properties")) {
                properties.load(is);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return properties.getProperty(name);
        }
    }
}
