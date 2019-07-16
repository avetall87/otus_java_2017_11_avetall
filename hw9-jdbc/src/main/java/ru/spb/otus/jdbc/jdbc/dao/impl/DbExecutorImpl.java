package ru.spb.otus.jdbc.jdbc.dao.impl;

import ru.spb.otus.jdbc.jdbc.dao.DbExecutor;
import ru.spb.otus.jdbc.jdbc.dao.utils.DBConnectionHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class DbExecutorImpl extends DBConnectionHelper implements DbExecutor {

    @Override
    public void executeStatement(String sql) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
