package service;

import domain.UserDataSet;

import java.util.List;

public interface DbService {

    void save(UserDataSet dataSet);

    UserDataSet findById(long id);

    UserDataSet findByFirstName(String firstName);

    UserDataSet findByLastName(String lastName);

    UserDataSet findByPatronymic(String patronymic);

    List<UserDataSet> findAll();
}
