package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

import java.math.BigDecimal;

public record BienEntity(
    String id,
    String codigo,
    String nombre,
    String descripcion,
    String tipo,
    String unidadMedida,
    BigDecimal valorUnitario,
    String estado) {}
