package com.jcaa.usersmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.enums.EstadoBien;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import com.jcaa.usersmanagement.domain.valueobject.CodigoBien;
import com.jcaa.usersmanagement.domain.valueobject.NombreBien;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Bien")
class BienTest {

    private BienId id;
    private CodigoBien codigo;
    private NombreBien nombre;
    private BigDecimal valorUnitario;

    @BeforeEach
    void setUp() {
        id = new BienId("B-001");
        codigo = new CodigoBien("ACT-001");
        nombre = new NombreBien("Computador");
        valorUnitario = new BigDecimal("2500000.00");
    }

    @Test
    @DisplayName("create() debe crear un bien con estado DISPONIBLE")
    void shouldCreateBienWithAvailableStatus() {
        final Bien bien =
                Bien.create(
                        id, codigo, nombre, "Computador portátil", "Tecnología",
                        "Unidad", valorUnitario);

        assertAll(
                () -> assertEquals(id, bien.getId()),
                () -> assertEquals(codigo, bien.getCodigo()),
                () -> assertEquals(nombre, bien.getNombre()),
                () -> assertEquals(valorUnitario, bien.getValorUnitario()),
                () -> assertEquals(EstadoBien.DISPONIBLE, bien.getEstado()));
    }

    @Test
    @DisplayName("asignar() debe retornar una nueva instancia con estado ASIGNADO")
    void shouldAssignBienAndPreserveOtherFields() {
        final Bien disponible =
                Bien.create(
                        id, codigo, nombre, "Computador portátil", "Tecnología",
                        "Unidad", valorUnitario);

        final Bien asignado = disponible.asignar();

        assertAll(
                () -> assertNotSame(disponible, asignado),
                () -> assertEquals(EstadoBien.ASIGNADO, asignado.getEstado()),
                () -> assertEquals(id, asignado.getId()),
                () -> assertEquals(codigo, asignado.getCodigo()),
                () -> assertEquals(nombre, asignado.getNombre()),
                () -> assertEquals(valorUnitario, asignado.getValorUnitario()));
    }

    @Test
    @DisplayName("ponerEnMantenimiento() debe cambiar el estado del bien")
    void shouldPutBienInMaintenance() {
        final Bien disponible =
                Bien.create(
                        id, codigo, nombre, "Computador portátil", "Tecnología",
                        "Unidad", valorUnitario);

        final Bien mantenimiento = disponible.ponerEnMantenimiento();

        assertAll(
                () -> assertNotSame(disponible, mantenimiento),
                () -> assertEquals(EstadoBien.EN_MANTENIMIENTO, mantenimiento.getEstado()),
                () -> assertEquals(id, mantenimiento.getId()));
    }

    @Test
    @DisplayName("darDeBaja() debe cambiar el estado del bien")
    void shouldRetireBien() {
        final Bien disponible =
                Bien.create(
                        id, codigo, nombre, "Computador portátil", "Tecnología",
                        "Unidad", valorUnitario);

        final Bien dadoDeBaja = disponible.darDeBaja();

        assertAll(
                () -> assertNotSame(disponible, dadoDeBaja),
                () -> assertEquals(EstadoBien.DADO_DE_BAJA, dadoDeBaja.getEstado()),
                () -> assertEquals(id, dadoDeBaja.getId()));
    }
}