package ru.spb.otus.atm.dao.impl;

import org.springframework.stereotype.Service;
import ru.spb.otus.atm.dao.BanknoteStore;
import ru.spb.otus.atm.domain.BanknoteType;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by avetall  28.01.18.
 */
@Service
public class BanknoteStoreImpl extends HashMap<BanknoteType, Integer> implements BanknoteStore {

    @Override
    public void add(BanknoteType type, Integer count) {
        if (type != null && count != null)
            super.put(type,
                      Optional.ofNullable(super.get(type)).orElse(0)+count);
    }

    @Override
    public Integer getBanknoteSum() {
        Integer total = 0;

        for (Entry<BanknoteType, Integer> entry : this.entrySet()) {
            total+=(entry.getKey().getValue() * entry.getValue());
        }
        return total;
    }

    @Override
    public Integer getCountBanknotesByType(BanknoteType type) {
        return Optional.ofNullable(super.get(type)).orElse(0);
    }

    @Override
    public void setBalance(BanknoteType type, Integer count) {

    }
}
