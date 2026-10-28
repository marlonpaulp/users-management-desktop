package com.jcaa.usersmanagement.domain.exception;

public final class InvalidNombreBienException extends DomainException {

    private static final String MESSAGE_EMPTY =
            "The asset name must not be empty.";

    private static final String MESSAGE_TOO_SHORT =
            "The asset name must have at least %d characters.";

    private InvalidNombreBienException(final String message) {
        super(message);
    }

    public static InvalidNombreBienException becauseValueIsEmpty() {
        return new InvalidNombreBienException(MESSAGE_EMPTY);
    }

    public static InvalidNombreBienException becauseLengthIsTooShort(
            final int minimumLength) {
        return new InvalidNombreBienException(
                String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}