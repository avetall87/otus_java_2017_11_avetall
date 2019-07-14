package ru.spb.otus.jdbc.jdbc.domain;

import lombok.Data;

@Data
public class UserDataSet extends DataSet {
    private String name;
    private Integer age;
}
