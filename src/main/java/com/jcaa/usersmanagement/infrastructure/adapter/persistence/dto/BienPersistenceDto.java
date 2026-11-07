package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

import java.math.BigDecimal;

public record BienPersistenceDto(
    String id,
    String codigo,
    String nombre,
    String descripcion,
    String tipo,
    String unidadMedida,
    BigDecimal valorUnitario,
    String estado) {

}
