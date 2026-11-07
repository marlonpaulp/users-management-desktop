package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateBienUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteBienUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllBienesUseCase;
import com.jcaa.usersmanagement.application.port.in.GetBienByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateBienUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.BienResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateBienRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateBienRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.BienDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class BienController {

    private final CreateBienUseCase createBienUseCase;
    private final UpdateBienUseCase updateBienUseCase;
    private final DeleteBienUseCase deleteBienUseCase;
    private final GetBienByIdUseCase getBienByIdUseCase;
    private final GetAllBienesUseCase getAllBienesUseCase;

    public List<BienResponse> listAllBienes() {
        final var bienes = getAllBienesUseCase.execute();
        return BienDesktopMapper.toResponseList(bienes);
    }

    public BienResponse findBienById(final String id) {
        final var query = BienDesktopMapper.toGetByIdQuery(id);
        final var bien = getBienByIdUseCase.execute(query);
        return BienDesktopMapper.toResponse(bien);
    }

    public BienResponse createBien(final CreateBienRequest request) {
        final var command = BienDesktopMapper.toCreateCommand(request);
        final var bien = createBienUseCase.execute(command);
        return BienDesktopMapper.toResponse(bien);
    }

    public BienResponse updateBien(final UpdateBienRequest request) {
        final var command = BienDesktopMapper.toUpdateCommand(request);
        final var bien = updateBienUseCase.execute(command);
        return BienDesktopMapper.toResponse(bien);
    }

    public void deleteBien(final String id) {
        final var command = BienDesktopMapper.toDeleteCommand(id);
        deleteBienUseCase.execute(command);
    }
}