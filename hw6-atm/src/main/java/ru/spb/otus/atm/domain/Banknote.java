package ru.spb.otus.atm.domain;

import lombok.Getter;

/**
 * Created by avetall  28.01.18.
 */
@Getter
public class Banknote {
    BanknoteType type;

    public Banknote(BanknoteType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.valueOf(type.getValue());
    }
}
