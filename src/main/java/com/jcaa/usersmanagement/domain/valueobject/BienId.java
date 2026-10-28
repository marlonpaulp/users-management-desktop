package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidBienIdException;
import java.util.Objects;

public record BienId(String value) {

    public BienId {
        final String normalizedValue =
                Objects.requireNonNull(value, "BienId cannot be null").trim();

        if (normalizedValue.isEmpty()) {
            throw InvalidBienIdException.becauseValueIsEmpty();
        }

        value = normalizedValue;
    }

    @Override
    public String toString() {
        return value;
    }
}