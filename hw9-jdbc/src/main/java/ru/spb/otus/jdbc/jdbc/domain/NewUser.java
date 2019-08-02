package ru.spb.otus.jdbc.jdbc.domain;

import lombok.Data;

@Data
public class NewUser extends DataSet {
    private String name;
    private Integer age;
}
