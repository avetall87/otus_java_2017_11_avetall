package ru.spb.otus.atmDepartmnent.department;

import ru.spb.otus.atmDepartmnent.atm.EventListener;
import ru.spb.otus.atmDepartmnent.atm.service.BanknoteAtm;

import java.util.ArrayList;

/**
 * Created by avetall  02.05.18.
 */
public class BanknoteAtmList extends ArrayList<BanknoteAtm> implements EventListener {

    @Override
    public void update() {
        this.forEach(BanknoteAtm::init);
    }
}
