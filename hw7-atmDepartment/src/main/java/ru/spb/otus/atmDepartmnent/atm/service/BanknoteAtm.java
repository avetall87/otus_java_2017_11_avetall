package ru.spb.otus.atmDepartmnent.atm.service;

import lombok.Getter;
import ru.spb.otus.atmDepartmnent.atm.EventListener;
import ru.spb.otus.atmDepartmnent.atm.domain.Banknote;
import ru.spb.otus.atmDepartmnent.atm.domain.BanknoteType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avetall  28.01.18.
 */
public class BanknoteAtm{

    private Atm atm;
    private List<BanknoteType> banknoteTypes;
    private BanknoteType defaultBanknote;

    public BanknoteAtm(Atm atm, BanknoteType defaultBanknote) {
        this.atm = atm;
        banknoteTypes = List.of(BanknoteType.values());
        this.defaultBanknote = defaultBanknote;

        init();
    }

    public void init (){
        atm.clear();
        atm.deposit(List.of(new Banknote(defaultBanknote)));
    }

    public Integer getBalance(){
        return atm.balance();
    }

    public List<Banknote> getSum(Integer sum){
        return atm.getBanknotes(sum);
    }

    /**
     * Receive denomination list - List<Integer>
     * @return List<Integer> - Return wrong denomination list
     */
    public List<Integer> receive(List<Integer> denominations){
        RawBanknote rawBanknote = new RawBanknote();
        rawBanknote.sort(denominations);
        atm.deposit(rawBanknote.getBanknotes());
        return rawBanknote.getWrongDenominations();
    }

    @Getter
    private class RawBanknote {
        private List<Banknote> banknotes;
        private List<Integer> wrongDenominations;

        RawBanknote() {
            this.banknotes = new ArrayList<>();
            this.wrongDenominations = new ArrayList<>();
        }

        private void sort(List<Integer> denominations){
            if(denominations != null){
                denominations.forEach(denomination ->{
                    BanknoteType type = getBanknoteType(denomination);
                    if (type != null)
                        banknotes.add(new Banknote(type));
                    else
                        wrongDenominations.add(denomination);
                });
            }
        }

        private BanknoteType getBanknoteType(Integer denomination){
            BanknoteType result = null;

            for (BanknoteType banknoteType: banknoteTypes) {
                if (banknoteType.getValue().equals(denomination))
                    result = banknoteType;
            }

            return result;
        }
    }

}
