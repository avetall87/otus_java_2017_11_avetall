package ru.spb.otus.jdbc.jdbc.dao;

import ru.spb.otus.jdbc.jdbc.domain.DataSet;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DbExecutor {

    void executeStatement(String sql);

    <T extends DataSet> T findById(Long id, Class<T> clazz) throws IllegalAccessException, InstantiationException, SQLException, InvocationTargetException;
}
