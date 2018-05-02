package ru.spb.otus.atmDepartmnent.atm.service.exception;

/**
 * Created by avetall  28.01.18.
 */
public class EmptyRequestedSumException extends NullPointerException {
    public EmptyRequestedSumException() {
        super("Requested sum is null !");
    }
}
