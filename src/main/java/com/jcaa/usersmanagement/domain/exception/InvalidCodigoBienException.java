package com.jcaa.usersmanagement.domain.exception;

public final class InvalidCodigoBienException extends DomainException {

    private static final String MESSAGE_EMPTY =
            "The asset code must not be empty.";

    private InvalidCodigoBienException(final String message) {
        super(message);
    }

    public static InvalidCodigoBienException becauseValueIsEmpty() {
        return new InvalidCodigoBienException(MESSAGE_EMPTY);
    }
}