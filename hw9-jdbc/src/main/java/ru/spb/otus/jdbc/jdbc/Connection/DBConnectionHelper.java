package ru.spb.otus.jdbc.jdbc.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectionHelper {

    public Connection getConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());

            String url = "jdbc:postgresql://localhost/postgres";

            Properties props = new Properties();
            props.setProperty("user","postgres");
            props.setProperty("password","docker");
            return DriverManager.getConnection(url, props);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
