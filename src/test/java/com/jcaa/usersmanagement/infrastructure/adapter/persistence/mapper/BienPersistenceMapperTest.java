package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.enums.EstadoBien;
import com.jcaa.usersmanagement.domain.model.Bien;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import com.jcaa.usersmanagement.domain.valueobject.CodigoBien;
import com.jcaa.usersmanagement.domain.valueobject.NombreBien;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.BienPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.BienEntity;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BienPersistenceMapper")
class BienPersistenceMapperTest {

    private static final String ID = "b-001";
    private static final String CODIGO = "INV-001";
    private static final String NOMBRE = "Computador";
    private static final String DESCRIPCION = "Computador de escritorio";
    private static final String TIPO = "Equipo tecnológico";
    private static final String UNIDAD_MEDIDA = "Unidad";
    private static final BigDecimal VALOR_UNITARIO = new BigDecimal("2500000.00");
    private static final String ESTADO = "DISPONIBLE";

    private Bien bien;
    private BienPersistenceDto dto;
    private BienEntity entity;

    @BeforeEach
    void setUp() {
        bien = Bien.create(
                new BienId(ID),
                new CodigoBien(CODIGO),
                new NombreBien(NOMBRE),
                DESCRIPCION,
                TIPO,
                UNIDAD_MEDIDA,
                VALOR_UNITARIO);

        dto = new BienPersistenceDto(
                ID, CODIGO, NOMBRE, DESCRIPCION, TIPO,
                UNIDAD_MEDIDA, VALOR_UNITARIO, ESTADO);

        entity = new BienEntity(
                ID, CODIGO, NOMBRE, DESCRIPCION, TIPO,
                UNIDAD_MEDIDA, VALOR_UNITARIO, ESTADO);
    }

    @Test
    @DisplayName("fromModelToDto() maps all Bien fields")
    void shouldMapModelToDto() {
        final BienPersistenceDto result =
                BienPersistenceMapper.fromModelToDto(bien);

        assertAll(
                "fromModelToDto()",
                () -> assertEquals(ID, result.id()),
                () -> assertEquals(CODIGO, result.codigo()),
                () -> assertEquals(NOMBRE, result.nombre()),
                () -> assertEquals(DESCRIPCION, result.descripcion()),
                () -> assertEquals(TIPO, result.tipo()),
                () -> assertEquals(UNIDAD_MEDIDA, result.unidadMedida()),
                () -> assertEquals(VALOR_UNITARIO, result.valorUnitario()),
                () -> assertEquals(ESTADO, result.estado()));
    }

    @Test
    @DisplayName("fromDtoToEntity() maps all DTO fields")
    void shouldMapDtoToEntity() {
        final BienEntity result =
                BienPersistenceMapper.fromDtoToEntity(dto);

        assertAll(
                "fromDtoToEntity()",
                () -> assertEquals(ID, result.id()),
                () -> assertEquals(CODIGO, result.codigo()),
                () -> assertEquals(NOMBRE, result.nombre()),
                () -> assertEquals(DESCRIPCION, result.descripcion()),
                () -> assertEquals(TIPO, result.tipo()),
                () -> assertEquals(UNIDAD_MEDIDA, result.unidadMedida()),
                () -> assertEquals(VALOR_UNITARIO, result.valorUnitario()),
                () -> assertEquals(ESTADO, result.estado()));
    }

    @Test
    @DisplayName("fromEntityToDto() maps all Entity fields")
    void shouldMapEntityToDto() {
        final BienPersistenceDto result =
                BienPersistenceMapper.fromEntityToDto(entity);

        assertAll(
                "fromEntityToDto()",
                () -> assertEquals(ID, result.id()),
                () -> assertEquals(CODIGO, result.codigo()),
                () -> assertEquals(NOMBRE, result.nombre()),
                () -> assertEquals(DESCRIPCION, result.descripcion()),
                () -> assertEquals(TIPO, result.tipo()),
                () -> assertEquals(UNIDAD_MEDIDA, result.unidadMedida()),
                () -> assertEquals(VALOR_UNITARIO, result.valorUnitario()),
                () -> assertEquals(ESTADO, result.estado()));
    }

    @Test
    @DisplayName("fromDtoToModel() maps DTO fields to domain Bien")
    void shouldMapDtoToModel() {
        final Bien result = BienPersistenceMapper.fromDtoToModel(dto);

        assertAll(
                "fromDtoToModel()",
                () -> assertEquals(ID, result.getId().value()),
                () -> assertEquals(CODIGO, result.getCodigo().value()),
                () -> assertEquals(NOMBRE, result.getNombre().value()),
                () -> assertEquals(DESCRIPCION, result.getDescripcion()),
                () -> assertEquals(TIPO, result.getTipo()),
                () -> assertEquals(UNIDAD_MEDIDA, result.getUnidadMedida()),
                () -> assertEquals(VALOR_UNITARIO, result.getValorUnitario()),
                () -> assertEquals(EstadoBien.DISPONIBLE, result.getEstado()));
    }

    @Test
    @DisplayName("fromDtoListToModelList() maps all DTOs to domain models")
    void shouldMapDtoListToModelList() {
        final BienPersistenceDto secondDto = new BienPersistenceDto(
                "b-002", "INV-002", "Impresora",
                "Impresora institucional", "Equipo tecnológico",
                "Unidad", new BigDecimal("900000.00"), ESTADO);

        final List<Bien> result =
                BienPersistenceMapper.fromDtoListToModelList(List.of(dto, secondDto));

        assertAll(
                "fromDtoListToModelList()",
                () -> assertEquals(2, result.size()),
                () -> assertEquals(ID, result.get(0).getId().value()),
                () -> assertEquals("b-002", result.get(1).getId().value()),
                () -> assertEquals("Impresora", result.get(1).getNombre().value()));
    }

    @Test
    @DisplayName("fromDtoListToModelList() returns an empty list when input is empty")
    void shouldReturnEmptyListWhenInputIsEmpty() {
        final List<Bien> result =
                BienPersistenceMapper.fromDtoListToModelList(List.of());

        assertTrue(result.isEmpty());
    }
}