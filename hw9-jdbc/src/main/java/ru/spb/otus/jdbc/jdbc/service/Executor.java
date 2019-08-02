package ru.spb.otus.jdbc.jdbc.service;

import ru.spb.otus.jdbc.jdbc.domain.DataSet;

import java.sql.SQLException;

public interface Executor {

    <T extends DataSet> void save(T user);

    <T extends DataSet> T load(long id, Class<T> clazz) throws IllegalAccessException, SQLException, InstantiationException;
}
