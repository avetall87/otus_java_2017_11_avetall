package ru.spb.otus.atmDepartmnent.atm.service;

import ru.spb.otus.atmDepartmnent.atm.domain.Banknote;

import java.util.List;
/**
 * Created by avetall  28.01.18.
 */
public interface Atm {
    void deposit(List<Banknote> banknotes);
    List<Banknote> getBanknotes(Integer sum);
    Integer balance();
    void clear();

}
