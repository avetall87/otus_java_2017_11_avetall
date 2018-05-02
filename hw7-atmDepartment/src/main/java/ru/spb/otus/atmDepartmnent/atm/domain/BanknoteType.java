package ru.spb.otus.atmDepartmnent.atm.domain;

import java.util.Arrays;

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

    public static BanknoteType getBanknoteTypeByIntegerValue(Integer value){
        BanknoteType result = null;
        for (BanknoteType banknoteType: BanknoteType.values()) {
            if (banknoteType.value.equals(value)){
                result = banknoteType;
            }
        }
        if (result == null){
            throw new IllegalArgumentException("Banknote type must be like: "+ Arrays.toString(BanknoteType.values()));
        }
        return result;
    }

}
