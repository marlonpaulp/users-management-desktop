package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidNombreBienException;
import java.util.Objects;

public record NombreBien(String value) {

    private static final int MINIMUM_LENGTH = 3;

    public NombreBien {
        final String normalizedValue =
                Objects.requireNonNull(value, "NombreBien cannot be null").trim();

        if (normalizedValue.isEmpty()) {
            throw InvalidNombreBienException.becauseValueIsEmpty();
        }

        if (normalizedValue.length() < MINIMUM_LENGTH) {
            throw InvalidNombreBienException.becauseLengthIsTooShort(MINIMUM_LENGTH);
        }

        value = normalizedValue;
    }

    @Override
    public String toString() {
        return value;
    }
}