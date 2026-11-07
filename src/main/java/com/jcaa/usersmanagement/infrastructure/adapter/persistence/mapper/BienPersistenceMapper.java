package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.EstadoBien;
import com.jcaa.usersmanagement.domain.model.Bien;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import com.jcaa.usersmanagement.domain.valueobject.CodigoBien;
import com.jcaa.usersmanagement.domain.valueobject.NombreBien;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.BienPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.BienEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BienPersistenceMapper {

    public BienPersistenceDto fromModelToDto(final Bien bien) {
        return new BienPersistenceDto(
                bien.getId().value(),
                bien.getCodigo().value(),
                bien.getNombre().value(),
                bien.getDescripcion(),
                bien.getTipo(),
                bien.getUnidadMedida(),
                bien.getValorUnitario(),
                bien.getEstado().name());
    }

    public BienEntity fromDtoToEntity(final BienPersistenceDto dto) {
        return new BienEntity(
                dto.id(),
                dto.codigo(),
                dto.nombre(),
                dto.descripcion(),
                dto.tipo(),
                dto.unidadMedida(),
                dto.valorUnitario(),
                dto.estado());
    }

    public BienPersistenceDto fromEntityToDto(final BienEntity entity) {
        return new BienPersistenceDto(
                entity.id(),
                entity.codigo(),
                entity.nombre(),
                entity.descripcion(),
                entity.tipo(),
                entity.unidadMedida(),
                entity.valorUnitario(),
                entity.estado());
    }

    public Bien fromDtoToModel(final BienPersistenceDto dto) {
        return new Bien(
                new BienId(dto.id()),
                new CodigoBien(dto.codigo()),
                new NombreBien(dto.nombre()),
                dto.descripcion(),
                dto.tipo(),
                dto.unidadMedida(),
                dto.valorUnitario(),
                EstadoBien.valueOf(dto.estado()));
    }

    public List<Bien> fromDtoListToModelList(final List<BienPersistenceDto> dtos) {
        final List<Bien> bienes = new ArrayList<>();

        for (final BienPersistenceDto dto : dtos) {
            bienes.add(fromDtoToModel(dto));
        }

        return bienes;
    }
}
