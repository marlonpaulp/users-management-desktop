package com.jcaa.usersmanagement.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jcaa.usersmanagement.domain.exception.InvalidCodigoBienException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CodigoBienTest {

    @ParameterizedTest
    @ValueSource(strings = {" COD-001 ", "  COD-001  ", "COD-001\t"})
    @DisplayName("Should create CodigoBien with trimmed value")
    void shouldCreateCodigoBienWithTrimmedValue(String input) {
        final CodigoBien codigoBien = new CodigoBien(input);

        assertEquals("COD-001", codigoBien.toString());
    }

    @Test
    @DisplayName("Should throw NullPointerException when code is null")
    void shouldThrowNullPointerExceptionWhenCodeIsNull() {
        assertThrows(NullPointerException.class, () -> new CodigoBien(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", "\f", "\b"})
    @DisplayName("Should throw InvalidCodigoBienException when value is empty")
    void shouldThrowInvalidCodigoBienExceptionWhenValueIsEmpty(String input) {
        assertThrows(InvalidCodigoBienException.class, () -> new CodigoBien(input));
    }
}
