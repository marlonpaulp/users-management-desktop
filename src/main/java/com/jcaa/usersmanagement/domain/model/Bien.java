package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.EstadoBien;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import com.jcaa.usersmanagement.domain.valueobject.CodigoBien;
import com.jcaa.usersmanagement.domain.valueobject.NombreBien;
import java.math.BigDecimal;
import lombok.Value;

@Value
public class Bien {

    BienId id;
    CodigoBien codigo;
    NombreBien nombre;
    String descripcion;
    String tipo;
    String unidadMedida;
    BigDecimal valorUnitario;
    EstadoBien estado;

    public static Bien create(
            final BienId id,
            final CodigoBien codigo,
            final NombreBien nombre,
            final String descripcion,
            final String tipo,
            final String unidadMedida,
            final BigDecimal valorUnitario) {

        return new Bien(
                id,
                codigo,
                nombre,
                descripcion,
                tipo,
                unidadMedida,
                valorUnitario,
                EstadoBien.DISPONIBLE);
    }

    public Bien asignar() {
        return new Bien(
                id, codigo, nombre, descripcion, tipo, unidadMedida, valorUnitario, EstadoBien.ASIGNADO);
    }

    public Bien ponerEnMantenimiento() {
        return new Bien(
                id,
                codigo,
                nombre,
                descripcion,
                tipo,
                unidadMedida,
                valorUnitario,
                EstadoBien.EN_MANTENIMIENTO);
    }

    public Bien darDeBaja() {
        return new Bien(
                id,
                codigo,
                nombre,
                descripcion,
                tipo,
                unidadMedida,
                valorUnitario,
                EstadoBien.DADO_DE_BAJA);
    }
}