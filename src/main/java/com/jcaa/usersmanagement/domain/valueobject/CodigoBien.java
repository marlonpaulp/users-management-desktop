package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidCodigoBienException;
import java.util.Objects;

public record CodigoBien(String value) {

    public CodigoBien {
        final String normalizedValue =
                Objects.requireNonNull(value, "CodigoBien cannot be null").trim();

        if (normalizedValue.isEmpty()) {
            throw InvalidCodigoBienException.becauseValueIsEmpty();
        }

        value = normalizedValue;
    }

    @Override
    public String toString() {
        return value;
    }
}
