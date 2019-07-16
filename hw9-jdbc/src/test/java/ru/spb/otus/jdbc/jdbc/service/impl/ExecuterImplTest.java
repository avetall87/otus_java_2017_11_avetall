package ru.spb.otus.jdbc.jdbc.service.impl;


import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.spb.otus.jdbc.jdbc.dao.impl.DbExecutorImpl;
import ru.spb.otus.jdbc.jdbc.dao.utils.DBInitialize;
import ru.spb.otus.jdbc.jdbc.domain.DataSet;
import ru.spb.otus.jdbc.jdbc.service.Executer;

import java.util.function.Consumer;

class ExecuterImplTest {

    private Executer executer;
    private PostgreSQLContainer postgreSQLContainer;

    @BeforeEach
    void setUp() {

        int hostPort = 5432;
        int containerExposedPort = 5432;
        Consumer<CreateContainerCmd> cmd = e -> e.withPortBindings(new PortBinding(Ports.Binding.bindPort(hostPort), new ExposedPort(containerExposedPort)));

        postgreSQLContainer = new PostgreSQLContainer("postgres:9.6.2")
                .withUsername("postgres")
                .withPassword("docker")
                .withDatabaseName("postgres");
        postgreSQLContainer.addExposedPort(5432);
        postgreSQLContainer.withCreateContainerCmdModifier(cmd);

        postgreSQLContainer.start();

        executer = new ExecuterImpl(new DbExecutorImpl());

        new DBInitialize().initStructure();
    }

    @AfterEach
    void tearDown(){
        postgreSQLContainer.stop();
    }

    @Test
    void save() {
        NewUser dataSet = new NewUser();
        dataSet.setId(1L);
        dataSet.setName("Super test Test");
        dataSet.setAge(30);

        executer.save(dataSet);

        System.out.println("End test !");
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