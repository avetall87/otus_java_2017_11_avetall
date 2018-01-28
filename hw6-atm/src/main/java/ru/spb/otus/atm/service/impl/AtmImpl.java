package ru.spb.otus.atm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spb.otus.atm.dao.BanknoteStore;
import ru.spb.otus.atm.domain.Banknote;
import ru.spb.otus.atm.domain.BanknoteType;
import ru.spb.otus.atm.service.Atm;
import ru.spb.otus.atm.service.exception.EmptyRequestedSumException;
import ru.spb.otus.atm.service.exception.LargeRequestedSumException;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by avetall  28.01.18.
 */
@Service
public class AtmImpl implements Atm {

    private BanknoteStore store;

    @Autowired
    public AtmImpl(BanknoteStore store) {
        this.store = store;
    }

    @Override
    public void deposit(List<Banknote> banknotes) {
        Map<BanknoteType, Long> collect = banknotes.stream()
                .collect(groupingBy(Banknote::getType, counting()));

        collect.forEach((type,count)-> store.add(type,count.intValue()));
    }

    @Override
    public List<Banknote> getBanknotes(Integer sum) {
        checkRequestedSum(sum);




        return null;
    }

    private void checkRequestedSum(Integer sum){
        if (sum == null)
            throw new EmptyRequestedSumException();

        if (sum > store.getBanknoteSum())
            throw new LargeRequestedSumException();

    }

    @Override
    public Integer balance() {
        return store.getBanknoteSum();
    }
}
