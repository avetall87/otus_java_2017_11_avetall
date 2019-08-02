package ru.spb.otus.jdbc.jdbc.dao.impl;

import lombok.SneakyThrows;
import ru.spb.otus.jdbc.jdbc.dao.DbExecutor;
import ru.spb.otus.jdbc.jdbc.dao.utils.DBConnectionHelper;
import ru.spb.otus.jdbc.jdbc.domain.DataSet;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class DbExecutorImpl extends DBConnectionHelper implements DbExecutor {

    @Override
    public void executeStatement(String sql) {
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SneakyThrows({IllegalAccessException.class, InstantiationException.class, SQLException.class, InvocationTargetException.class})
    @Override
    public <T extends DataSet> T findById(Long id, Class<T> clazz) {

        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];

        if (!constructor.isAccessible()){
            constructor.setAccessible(true);
        }

        T instance = (T) constructor.newInstance();

        List<String> methodNames = Arrays.stream(instance.getClass().getMethods()).map(method -> method.getName()).collect(Collectors.toList());

        String sql = "select * from users where id = ?";
        ResultSet resultSet = null;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                instance.setId(resultSet.getLong("id"));

                if (methodNames.contains("setName")) {
                    instance.getClass().getMethod("setName", String.class).invoke(instance, resultSet.getString("name"));
                }

                if (methodNames.contains("setAge")) {
                    instance.getClass().getMethod("setAge", Integer.class).invoke(instance, resultSet.getInt("age"));
                }
            }

            resultSet.close();
            return instance;
        } catch (Exception ex) {

            if (resultSet != null) {
                resultSet.close();
            }

            ex.printStackTrace();
            return null;
        }
    }

    private <T extends DataSet> T getDataFromStatement(PreparedStatement statement) {

        T result = null;

        try (ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        finally {
            return result;
        }
    }

}
