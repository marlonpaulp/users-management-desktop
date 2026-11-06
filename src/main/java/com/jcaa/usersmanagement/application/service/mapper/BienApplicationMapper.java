package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateBienCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteBienCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateBienCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetBienByIdQuery;
import com.jcaa.usersmanagement.domain.enums.EstadoBien;
import com.jcaa.usersmanagement.domain.model.Bien;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import com.jcaa.usersmanagement.domain.valueobject.CodigoBien;
import com.jcaa.usersmanagement.domain.valueobject.NombreBien;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BienApplicationMapper {

    public Bien fromCreateCommandToModel(final CreateBienCommand command) {
        return Bien.create(
                new BienId(command.id()),
                new CodigoBien(command.codigo()),
                new NombreBien(command.nombre()),
                command.descripcion(),
                command.tipo(),
                command.unidadMedida(),
                command.valorUnitario());
    }

    public Bien fromUpdateCommandToModel(final UpdateBienCommand command) {
        return new Bien(
                new BienId(command.id()),
                new CodigoBien(command.codigo()),
                new NombreBien(command.nombre()),
                command.descripcion(),
                command.tipo(),
                command.unidadMedida(),
                command.valorUnitario(),
                EstadoBien.valueOf(command.estado().trim().toUpperCase()));
    }

    public BienId fromGetBienByIdQueryToBienId(final GetBienByIdQuery query) {
        return new BienId(query.id());
    }

    public BienId fromDeleteCommandToBienId(final DeleteBienCommand command) {
        return new BienId(command.id());
    }
}
