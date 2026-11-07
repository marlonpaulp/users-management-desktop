package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.BienResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.BienController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.BienResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListBienesHandler implements OperationHandler {

    private final BienController bienController;
    private final BienResponsePrinter printer;

    @Override
    public void handle() {
        final List<BienResponse> bienes = bienController.listAllBienes();
        printer.printList(bienes);
    }
}