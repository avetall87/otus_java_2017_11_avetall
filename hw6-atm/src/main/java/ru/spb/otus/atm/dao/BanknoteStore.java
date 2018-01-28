package ru.spb.otus.atm.dao;

import ru.spb.otus.atm.domain.BanknoteType;

import java.util.Map;

/**
 * Created by avetall  28.01.18.
 */
public interface BanknoteStore {
    void add(BanknoteType type, Integer count);
    Integer getSum(Integer count);
    Integer getBanknoteSum();
    Map<BanknoteType,Integer> computeForSum(Integer sum);

}
