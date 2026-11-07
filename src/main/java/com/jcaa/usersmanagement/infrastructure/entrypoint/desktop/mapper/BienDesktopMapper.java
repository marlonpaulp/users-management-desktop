package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateBienCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteBienCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateBienCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetBienByIdQuery;
import com.jcaa.usersmanagement.domain.model.Bien;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.BienResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateBienRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateBienRequest;

import java.util.List;

public final class BienDesktopMapper {

    private BienDesktopMapper() {}

    public static CreateBienCommand toCreateCommand(final CreateBienRequest request) {
        return new CreateBienCommand(
                request.id(),
                request.codigo(),
                request.nombre(),
                request.descripcion(),
                request.tipo(),
                request.unidadMedida(),
                request.valorUnitario());
    }

    public static UpdateBienCommand toUpdateCommand(final UpdateBienRequest request) {
        return new UpdateBienCommand(
                request.id(),
                request.codigo(),
                request.nombre(),
                request.descripcion(),
                request.tipo(),
                request.unidadMedida(),
                request.valorUnitario(),
                request.estado());
    }

    public static DeleteBienCommand toDeleteCommand(final String id) {
        return new DeleteBienCommand(id);
    }

    public static GetBienByIdQuery toGetByIdQuery(final String id) {
        return new GetBienByIdQuery(id);
    }

    public static BienResponse toResponse(final Bien bien) {
        return new BienResponse(
                bien.getId().value(),
                bien.getCodigo().value(),
                bien.getNombre().value(),
                bien.getDescripcion(),
                bien.getTipo(),
                bien.getUnidadMedida(),
                bien.getValorUnitario(),
                bien.getEstado().name());
    }

    public static List<BienResponse> toResponseList(final List<Bien> bienes) {
        return bienes.stream().map(BienDesktopMapper::toResponse).toList();
    }
}