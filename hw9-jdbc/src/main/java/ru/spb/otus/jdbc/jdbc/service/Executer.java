package ru.spb.otus.jdbc.jdbc.service;

import ru.spb.otus.jdbc.jdbc.domain.DataSet;

public interface Executer {

    <T extends DataSet> void save(T user);

    <T extends DataSet> T load(long id, Class<T> clazz);
}
