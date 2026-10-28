package com.jcaa.usersmanagement.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jcaa.usersmanagement.domain.exception.InvalidBienIdException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BienIdTest {

    @ParameterizedTest
    @ValueSource(strings = {" bien123 ", "  bien123  ", "bien123\t"})
    @DisplayName("Should create BienId with trimmed value")
    void shouldCreateBienIdWithTrimmedValue(String input) {
        final BienId bienId = new BienId(input);

        assertEquals("bien123", bienId.toString());
    }

    @Test
    @DisplayName("Should throw NullPointerException when BienId is null")
    void shouldThrowNullPointerExceptionWhenBienIdIsNull() {
        assertThrows(NullPointerException.class, () -> new BienId(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n", "\r", "\f", "\b"})
    @DisplayName("Should throw InvalidBienIdException when value is empty")
    void shouldThrowInvalidBienIdExceptionWhenValueIsEmpty(String input) {
        assertThrows(InvalidBienIdException.class, () -> new BienId(input));
    }
}
