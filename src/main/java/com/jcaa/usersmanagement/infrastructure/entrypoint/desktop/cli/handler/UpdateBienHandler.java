package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.BienResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.BienController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateBienRequest;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateBienHandler implements OperationHandler {

    private final BienController bienController;
    private final ConsoleIO console;
    private final BienResponsePrinter printer;

    @Override
    public void handle() {
        console.println("\n  === Update Bien ===");

        final String id = console.readRequired("  ID del bien: ");
        final String codigo = console.readRequired("  Codigo: ");
        final String nombre = console.readRequired("  Nombre: ");
        final String descripcion = console.readRequired("  Descripcion: ");
        final String tipo = console.readRequired("  Tipo: ");
        final String unidadMedida = console.readRequired("  Unidad de medida: ");
        final BigDecimal valorUnitario =
                new BigDecimal(console.readRequired("  Valor unitario: "));
        final String estado = console.readRequired(
                "  Estado (DISPONIBLE, ASIGNADO, EN_MANTENIMIENTO, DADO_DE_BAJA): ");

        final UpdateBienRequest request = new UpdateBienRequest(
                id, codigo, nombre, descripcion, tipo,
                unidadMedida, valorUnitario, estado);

        final var bien = bienController.updateBien(request);

        console.println("\n  Bien actualizado correctamente.");
        printer.print(bien);
    }
}