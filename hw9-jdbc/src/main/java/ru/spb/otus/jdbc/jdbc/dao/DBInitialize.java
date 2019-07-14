package ru.spb.otus.jdbc.jdbc.dao;

import ru.spb.otus.jdbc.jdbc.Connection.DBConnectionHelper;

import java.sql.*;

public class DBInitialize extends DBConnectionHelper {

    private static final String CREATE_TABLE_USERS =
                    "create table if not exists  public.users\n" +
                    "(\n" +
                    "id bigserial constraint user_id PRIMARY KEY ,\n" +
                    "    name varchar (255) not null ,\n" +
                    "    age integer default 0\n" +
                    ")";

    public void initStructure() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_USERS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
