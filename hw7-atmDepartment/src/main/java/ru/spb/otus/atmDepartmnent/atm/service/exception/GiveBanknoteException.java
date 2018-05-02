package ru.spb.otus.atmDepartmnent.atm.service.exception;

/**
 * Created by avetall  30.01.18.
 */
public class GiveBanknoteException extends RuntimeException {
    public GiveBanknoteException() {
        super("Can't give amount requested banknote !");
    }
}
