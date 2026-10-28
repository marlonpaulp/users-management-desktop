package com.jcaa.usersmanagement.domain.exception;

public final class InvalidBienIdException extends DomainException {

    private static final String MESSAGE_EMPTY =
            "The asset id must not be empty.";

    private InvalidBienIdException(final String message) {
        super(message);
    }

    public static InvalidBienIdException becauseValueIsEmpty() {
        return new InvalidBienIdException(MESSAGE_EMPTY);
    }
}