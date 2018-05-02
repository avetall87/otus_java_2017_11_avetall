package ru.spb.otus.atmDepartmnent.department;

import lombok.Getter;
import ru.spb.otus.atmDepartmnent.atm.EventManager;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by avetall  08.04.18.
 */
@Getter
public class AtmDepartment {
    private ListBanknoteAtm atms;
    private EventManager events;
    public static final String INITIAL_STATE_EVENT= "initialState";

    public AtmDepartment(ListBanknoteAtm atms) {
        this.atms = atms;
        this.events = new EventManager(INITIAL_STATE_EVENT);
        events.subscribe(INITIAL_STATE_EVENT, atms);
    }

    public Integer getBalanceAmount(){
        AtomicReference<Integer> balance = new AtomicReference<>(0);
        atms.forEach(banknoteAtm -> balance.updateAndGet(value -> value + banknoteAtm.getBalance()));
        return balance.get();
    }

    public void setInitialState(){
        events.notify(INITIAL_STATE_EVENT);
    }
}
