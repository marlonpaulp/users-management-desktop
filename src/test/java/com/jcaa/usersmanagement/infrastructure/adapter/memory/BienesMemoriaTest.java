package com.jcaa.usersmanagement.infrastructure.adapter.memory;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.enums.EstadoBien;
import com.jcaa.usersmanagement.domain.model.Bien;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import com.jcaa.usersmanagement.domain.valueobject.CodigoBien;
import com.jcaa.usersmanagement.domain.valueobject.NombreBien;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BienesMemoria")
class BienesMemoriaTest {

    private BienesMemoria bienesMemoria;
    private Bien bien;

    @BeforeEach
    void setUp() {
        bienesMemoria = new BienesMemoria();

        bien = Bien.create(
                new BienId("B-001"),
                new CodigoBien("ACT-001"),
                new NombreBien("Computador"),
                "Computador portatil",
                "Tecnologia",
                "Unidad",
                new BigDecimal("2500000.00"));
    }

    @Test
    @DisplayName("save() debe guardar un bien correctamente")
    void shouldSaveBien() {
        final Bien resultado = bienesMemoria.save(bien);

        assertAll(
                () -> assertEquals(bien, resultado),
                () -> assertTrue(
                        bienesMemoria.getById(bien.getId()).isPresent()));
    }

    @Test
    @DisplayName("save() no debe permitir IDs duplicados")
    void shouldRejectDuplicateBienId() {
        bienesMemoria.save(bien);

        assertThrows(
                IllegalStateException.class,
                () -> bienesMemoria.save(bien));
    }

    @Test
    @DisplayName("getById() debe encontrar un bien existente")
    void shouldFindBienById() {
        bienesMemoria.save(bien);

        final Bien resultado =
                bienesMemoria.getById(new BienId("B-001")).orElseThrow();

        assertEquals(bien, resultado);
    }

    @Test
    @DisplayName("getById() debe retornar vacío si el bien no existe")
    void shouldReturnEmptyWhenBienDoesNotExist() {
        assertTrue(
                bienesMemoria.getById(new BienId("B-999")).isEmpty());
    }

    @Test
    @DisplayName("getAll() debe retornar todos los bienes")
    void shouldReturnAllBienes() {
        final Bien segundoBien = Bien.create(
                new BienId("B-002"),
                new CodigoBien("ACT-002"),
                new NombreBien("Impresora"),
                "Impresora laser",
                "Tecnologia",
                "Unidad",
                new BigDecimal("800000.00"));

        bienesMemoria.save(bien);
        bienesMemoria.save(segundoBien);

        final List<Bien> resultado = bienesMemoria.getAll();

        assertAll(
                () -> assertEquals(2, resultado.size()),
                () -> assertTrue(resultado.contains(bien)),
                () -> assertTrue(resultado.contains(segundoBien)));
    }

    @Test
    @DisplayName("update() debe actualizar un bien existente")
    void shouldUpdateBien() {
        bienesMemoria.save(bien);

        final Bien actualizado = Bien.create(
                bien.getId(),
                bien.getCodigo(),
                new NombreBien("Computador actualizado"),
                "Equipo actualizado",
                "Tecnologia",
                "Unidad",
                new BigDecimal("2800000.00"));

        final Bien resultado = bienesMemoria.update(actualizado);

        assertAll(
                () -> assertEquals(actualizado, resultado),
                () -> assertEquals(
                        actualizado,
                        bienesMemoria.getById(bien.getId()).orElseThrow()));
    }

    @Test
    @DisplayName("update() debe rechazar un bien inexistente")
    void shouldRejectUpdateWhenBienDoesNotExist() {
        assertThrows(
                IllegalStateException.class,
                () -> bienesMemoria.update(bien));
    }

    @Test
    @DisplayName("delete() debe eliminar un bien existente")
    void shouldDeleteBien() {
        bienesMemoria.save(bien);

        bienesMemoria.delete(bien.getId());

        assertTrue(bienesMemoria.getById(bien.getId()).isEmpty());
    }

    @Test
    @DisplayName("delete() no debe fallar si el bien no existe")
    void shouldDeleteNonExistingBienWithoutError() {
        assertDoesNotThrow(
                () -> bienesMemoria.delete(new BienId("B-999")));
    }

    @Test
    @DisplayName("getAll() debe retornar una lista vacía inicialmente")
    void shouldReturnEmptyListWhenNoBienesExist() {
        assertTrue(bienesMemoria.getAll().isEmpty());
    }
}