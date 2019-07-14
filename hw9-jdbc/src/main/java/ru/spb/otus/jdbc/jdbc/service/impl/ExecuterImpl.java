package ru.spb.otus.jdbc.jdbc.service.impl;

import lombok.SneakyThrows;
import ru.spb.otus.jdbc.jdbc.dao.DbExecuter;
import ru.spb.otus.jdbc.jdbc.domain.DataSet;
import ru.spb.otus.jdbc.jdbc.service.ClassMetadataService;
import ru.spb.otus.jdbc.jdbc.service.Executer;

import java.lang.reflect.Field;
import java.util.*;

public class ExecuterImpl implements Executer {

    private DbExecuter executer;

    public ExecuterImpl(DbExecuter executer) {
        this.executer = executer;
    }

    @SneakyThrows({IllegalAccessException.class, NoSuchFieldException.class})
    @Override
    public <T extends DataSet> void save(T user) {
        List<String> fieldNames = ClassMetadataService.getFieldNames(user.getClass());
        if (Objects.nonNull(fieldNames)) {
            if (fieldNames.size() == 2) {
                Map<String, String> objectData = new HashMap<>();
                for (String fieldName : fieldNames) {
                    Field declaredField = user.getClass().getDeclaredField(fieldName);
                    switch (fieldName) {
                        case "name":
                        case "age":
                            declaredField.setAccessible(true);
                            objectData.put(fieldName, String.valueOf(declaredField.get(user)));
                            break;
                        default:
                            throw new RuntimeException("Wrong class name !");
                    }
                }
                executer.executeStatement(saveSqlCreator(objectData));
            } else {
                throw new RuntimeException("Wrong fields count in object, must be two fields: name and age !");
            }
        }
    }

    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) {
        return null;
    }

    private String saveSqlCreator(Map<String, String> objectData) {

        String insert = "insert into users (";

        StringJoiner fields = new StringJoiner(",");
        StringJoiner values = new StringJoiner(",");

        for (String field : objectData.keySet()) {
            fields.add(field);
            values.add("'" + objectData.get(field) + "'");
        }

        return insert + fields.toString() + ")" + " values (" + values.toString() + ")";
    }
}
