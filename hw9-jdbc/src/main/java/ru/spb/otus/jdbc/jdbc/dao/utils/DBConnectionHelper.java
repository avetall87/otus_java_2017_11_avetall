package ru.spb.otus.jdbc.jdbc.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectionHelper {

    public Connection getConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());

            // default port is 5432
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
