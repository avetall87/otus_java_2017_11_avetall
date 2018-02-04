package ru.spb.otus.atm.service.exception;

/**
 * Created by avetall  04.02.18.
 */
public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
        super("Not enough money in ATM");
    }
}
