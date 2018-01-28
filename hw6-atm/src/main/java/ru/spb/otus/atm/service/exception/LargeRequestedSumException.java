package ru.spb.otus.atm.service.exception;

/**
 * Created by avetall  28.01.18.
 */
public class LargeRequestedSumException extends RuntimeException {
    public LargeRequestedSumException() {
        super("Requested sum more then has in atm !");
    }
}
