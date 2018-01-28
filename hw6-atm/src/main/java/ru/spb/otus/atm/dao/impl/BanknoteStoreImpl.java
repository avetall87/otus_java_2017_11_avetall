package ru.spb.otus.atm.dao.impl;

import org.springframework.stereotype.Service;
import ru.spb.otus.atm.dao.BanknoteStore;
import ru.spb.otus.atm.domain.BanknoteType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
    public Integer getSum(Integer count) {
        return 0;
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
    public Map<BanknoteType, Integer> computeForSum(Integer sum) {
        Map<BanknoteType, Integer> result = new HashMap<>();

        Arrays.asList(BanknoteType.values()).forEach(type->{
              result.put(type,(sum/get(type)));
        });

        return result;
    }
}
