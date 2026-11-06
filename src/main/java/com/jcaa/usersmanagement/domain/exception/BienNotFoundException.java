package com.jcaa.usersmanagement.domain.exception;

public final class BienNotFoundException extends DomainException {

    private BienNotFoundException(final String message) {
        super(message);
    }

    public static BienNotFoundException becauseIdWasNotFound(final String id) {
        return new BienNotFoundException("Bien with id " + id + " was not found");
    }
}
