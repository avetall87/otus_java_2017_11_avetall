package ru.spb.otus.jdbc.jdbc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.spb.otus.jdbc.jdbc.domain.UserDataSet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassMetadataServiceTest {

    private ClassMetadataService classMetadataService;

    @BeforeEach
    void setUp() {
        classMetadataService = new ClassMetadataService();
    }

    @Test
    void getFieldNames() {
        List<String> fieldNames = classMetadataService.getFieldNames(UserDataSet.class);
        assertTrue(fieldNames.containsAll(List.of("name", "age")));
    }

}