package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import java.math.BigDecimal;

public record CreateBienRequest(
        String id,
        String codigo,
        String nombre,
        String descripcion,
        String tipo,
        String unidadMedida,
        BigDecimal valorUnitario
) {}