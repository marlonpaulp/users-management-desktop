package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record UpdateBienCommand(
        @NotBlank(message = "id must not be blank")
        String id,

        @NotBlank(message = "codigo must not be blank")
        String codigo,

        @NotBlank(message = "nombre must not be blank")
        @Size(min = 3, message = "nombre must have at least 3 characters")
        String nombre,

        String descripcion,

        @NotBlank(message = "tipo must not be blank")
        String tipo,

        @NotBlank(message = "unidadMedida must not be blank")
        String unidadMedida,

        @NotNull(message = "valorUnitario must not be null")
        @DecimalMin(value = "0.0", inclusive = true,
                message = "valorUnitario must not be negative")
        BigDecimal valorUnitario,

        @NotBlank(message = "estado must not be blank")
        String estado
) {}