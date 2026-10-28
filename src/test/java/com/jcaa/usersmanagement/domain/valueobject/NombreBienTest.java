package com.jcaa.usersmanagement.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jcaa.usersmanagement.domain.exception.InvalidNombreBienException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NombreBienTest {

    @ParameterizedTest
    @ValueSource(strings = {" Computador ", "  Escritorio  ", "Impresora\t"})
    @DisplayName("Should create NombreBien with trimmed value")
    void shouldCreateNombreBienWithTrimmedValue(String input) {
        final NombreBien nombreBien = new NombreBien(input);

        assertEquals(input.trim(), nombreBien.toString());
    }

    @Test
    @DisplayName("Should throw NullPointerException when name is null")
    void shouldThrowNullPointerExceptionWhenNameIsNull() {
        assertThrows(NullPointerException.class, () -> new NombreBien(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", "\f", "\b"})
    @DisplayName("Should throw InvalidNombreBienException when value is empty")
    void shouldThrowInvalidNombreBienExceptionWhenValueIsEmpty(String input) {
        assertThrows(InvalidNombreBienException.class, () -> new NombreBien(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "AB", " A "})
    @DisplayName("Should throw InvalidNombreBienException when name is too short")
    void shouldThrowInvalidNombreBienExceptionWhenNameIsTooShort(String input) {
        assertThrows(InvalidNombreBienException.class, () -> new NombreBien(input));
    }
}
