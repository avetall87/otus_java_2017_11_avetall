package ru.spb.otus.jdbc.jdbc.service.impl;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.spb.otus.jdbc.jdbc.dao.impl.DbExecuterImpl;
import ru.spb.otus.jdbc.jdbc.domain.DataSet;
import ru.spb.otus.jdbc.jdbc.domain.UserDataSet;
import ru.spb.otus.jdbc.jdbc.service.Executer;

import static org.junit.jupiter.api.Assertions.*;

class ExecuterImplTest {

    private Executer executer;

    @BeforeEach
    void setUp() {

        executer = new ExecuterImpl(new DbExecuterImpl());
    }

    @Test
    void save() {
        NewUser dataSet = new NewUser();
        dataSet.setId(1L);
        dataSet.setName("Super test Test");
        dataSet.setAge(30);

        executer.save(dataSet);
    }


    private class NewUser extends DataSet {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}