package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.BienController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteBienHandler implements OperationHandler {

    private final BienController bienController;
    private final ConsoleIO console;

    @Override
    public void handle() {
        console.println("\n  === Delete Bien ===");

        final String id = console.readRequired("  Enter bien ID: ");

        bienController.deleteBien(id);

        console.println("  Bien eliminado correctamente.");
    }
}