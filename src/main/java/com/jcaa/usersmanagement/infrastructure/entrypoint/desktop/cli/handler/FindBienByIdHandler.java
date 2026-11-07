package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.BienResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.BienController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class FindBienByIdHandler implements OperationHandler {

    private final BienController bienController;
    private final ConsoleIO console;
    private final BienResponsePrinter printer;

    @Override
    public void handle() {
        final String id = console.readRequired("  Enter bien ID: ");
        final var bien = bienController.findBienById(id);
        printer.print(bien);
    }
}