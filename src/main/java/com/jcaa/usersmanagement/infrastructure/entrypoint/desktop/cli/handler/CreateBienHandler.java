package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.BienResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.BienController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateBienRequest;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateBienHandler implements OperationHandler {

    private final BienController bienController;
    private final ConsoleIO console;
    private final BienResponsePrinter printer;

    @Override
    public void handle() {
        console.println("\n  === Register New Bien ===");

        final String id = console.readRequired("  ID: ");
        final String codigo = console.readRequired("  Codigo: ");
        final String nombre = console.readRequired("  Nombre: ");
        final String descripcion = console.readRequired("  Descripcion: ");
        final String tipo = console.readRequired("  Tipo: ");
        final String unidadMedida = console.readRequired("  Unidad de medida: ");
        final BigDecimal valorUnitario =
                new BigDecimal(console.readRequired("  Valor unitario: "));

        final CreateBienRequest request = new CreateBienRequest(
                id, codigo, nombre, descripcion, tipo, unidadMedida, valorUnitario);

        final var bien = bienController.createBien(request);

        console.println("\n  Bien registrado correctamente.");
        printer.print(bien);
    }
}