package ru.spb.otus.atm.service.impl;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spb.otus.atm.dao.BanknoteStore;
import ru.spb.otus.atm.domain.Banknote;
import ru.spb.otus.atm.domain.BanknoteType;
import ru.spb.otus.atm.service.Atm;
import ru.spb.otus.atm.service.exception.EmptyRequestedSumException;
import ru.spb.otus.atm.service.exception.GiveBanknoteException;
import ru.spb.otus.atm.service.exception.LargeRequestedSumException;
import ru.spb.otus.atm.service.exception.NotEnoughMoneyException;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static ru.spb.otus.atm.domain.BanknoteType.*;

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

        List<Banknote> result = new ArrayList<>();

        // java 8
        final Integer[] calcResult = {0};

        Arrays.stream(BanknoteType.values())
                .sorted((o1, o2) -> (o2.getValue() > o1.getValue())? 1: -1)
                .collect(Collectors.toList())
                .forEach(banknoteType -> {
                    Integer countBanknotesByType = store.getCountBanknotesByType(banknoteType);

                    for (int i = 0; i < store.getCountBanknotesByType(banknoteType); i++) {
                        if (calcResult[0] > sum || banknoteType.getValue() > sum)
                            return;

                        if (banknoteType.getValue().equals(sum)){
                            result.add(new Banknote(banknoteType));
                            store.setBalance(banknoteType,countBanknotesByType--);
                            calcResult[0] += banknoteType.getValue();
                            return;
                        }else {
                            if ((calcResult[0] + banknoteType.getValue()) > sum)
                                return;

                            result.add(new Banknote(banknoteType));
                            store.setBalance(banknoteType,countBanknotesByType--);
                            calcResult[0] += banknoteType.getValue();
                        }

                    }

                });
        checkResult(result,sum);
        return result;
    }

    private void checkRequestedSum(Integer sum){
        if (sum == null)
            throw new EmptyRequestedSumException();

        if (sum > store.getBanknoteSum())
            throw new LargeRequestedSumException();

        if (sum % TEN.getValue() != 0)
            throw new GiveBanknoteException();

    }

    private void computeResult(Integer[] calcResult, BanknoteType banknoteType, Integer countBanknotesType){
        store.setBalance(banknoteType,countBanknotesType--);
        calcResult[0] += banknoteType.getValue();
    }

    private void checkResult(List<Banknote> banknotes, Integer sum){
        int total = banknotes.stream().map(item-> item.getType().getValue()).mapToInt(i->i).sum();

        if(total < sum)
            throw new NotEnoughMoneyException();
    }

    @Override
    public Integer balance() {
        return store.getBanknoteSum();
    }

}
