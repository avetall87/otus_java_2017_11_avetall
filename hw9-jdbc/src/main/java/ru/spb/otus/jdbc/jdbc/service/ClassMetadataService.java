package ru.spb.otus.jdbc.jdbc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassMetadataService {

    public static List<String> getFieldNames(Class aClass) {
        List<String> result = new ArrayList<>();
        Arrays.stream(aClass.getDeclaredFields()).filter(item-> !item.getName().contains("this")).forEach(field -> result.add(field.getName()));
        return result;
    }
}
