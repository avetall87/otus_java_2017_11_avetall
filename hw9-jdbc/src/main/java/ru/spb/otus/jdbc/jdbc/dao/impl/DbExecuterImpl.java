package ru.spb.otus.jdbc.jdbc.dao.impl;

import ru.spb.otus.jdbc.jdbc.dao.DbExecuter;
import ru.spb.otus.jdbc.jdbc.dao.utils.DBConnectionHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class DbExecuterImpl extends DBConnectionHelper implements DbExecuter {

    @Override
    public void executeStatement(String sql) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
