package ru.spb.otus.jdbc.jdbc.service.impl;


import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.spb.otus.jdbc.jdbc.dao.impl.DbExecutorImpl;
import ru.spb.otus.jdbc.jdbc.dao.utils.DBInitialize;
import ru.spb.otus.jdbc.jdbc.domain.NewUser;
import ru.spb.otus.jdbc.jdbc.service.Executor;

import java.sql.SQLException;
import java.util.function.Consumer;

class ExecutorImplTest {

    private Executor executor;
    private PostgreSQLContainer postgreSQLContainer;

    @BeforeEach
    void setUp() {

        int hostPort = 5432;
        int containerExposedPort = 5432;
        Consumer<CreateContainerCmd> cmd = e -> e.withPortBindings(new PortBinding(Ports.Binding.bindPort(hostPort), new ExposedPort(containerExposedPort)));

        postgreSQLContainer = new PostgreSQLContainer("postgres:11.4")
                .withUsername("postgres")
                .withPassword("docker")
                .withDatabaseName("postgres");
        postgreSQLContainer.addExposedPort(5432);
        postgreSQLContainer.withCreateContainerCmdModifier(cmd);

        postgreSQLContainer.start();

        executor = new ExecutorImpl(new DbExecutorImpl());

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

        executor.save(dataSet);

        System.out.println("End test !");
    }

    @Test
    void load() throws IllegalAccessException, SQLException, InstantiationException {
        NewUser dataSet = new NewUser();
        dataSet.setId(10L);
        dataSet.setName("Super test Test");
        dataSet.setAge(30);

        executor.save(dataSet);

        save();

        NewUser user = executor.load(1L, NewUser.class);

        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getName());
        Assertions.assertNotNull(user.getAge());
    }
}