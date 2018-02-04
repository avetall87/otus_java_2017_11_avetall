package ru.spb.otus.atm.domain;

import java.util.Collections;
import java.util.List;

/**
 * Created by avetall  28.01.18.
 */
public enum BanknoteType {

    TEN(10),
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    FIVE_THOUSAND(5000);

    private final Integer value;


    BanknoteType(Integer value) {
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }

}
