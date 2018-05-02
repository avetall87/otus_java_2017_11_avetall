package ru.spb.otus.atmDepartmnent.atm.dao;

import ru.spb.otus.atmDepartmnent.atm.domain.BanknoteType;

/**
 * Created by avetall  28.01.18.
 */
public interface BanknoteStore {
    void add(BanknoteType type, Integer count);
    Integer getBanknoteSum();
    Integer getCountBanknotesByType(BanknoteType type);
    void setBalance(BanknoteType type, Integer count);
    void clear();
}
