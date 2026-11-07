package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.BienResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class BienResponsePrinter {

    private static final String SEPARATOR = "-".repeat(52);
    private static final String ROW_FORMAT = "  %-18s : %s%n";

    private final ConsoleIO console;

    public void print(final BienResponse response) {
        console.println(SEPARATOR);
        console.printf(ROW_FORMAT, "ID", response.id());
        console.printf(ROW_FORMAT, "Código", response.codigo());
        console.printf(ROW_FORMAT, "Nombre", response.nombre());
        console.printf(ROW_FORMAT, "Descripción", response.descripcion());
        console.printf(ROW_FORMAT, "Tipo", response.tipo());
        console.printf(ROW_FORMAT, "Unidad medida", response.unidadMedida());
        console.printf(ROW_FORMAT, "Valor unitario", response.valorUnitario());
        console.printf(ROW_FORMAT, "Estado", response.estado());
        console.println(SEPARATOR);
    }

    public void printList(final List<BienResponse> bienes) {
        if (bienes.isEmpty()) {
            console.println("  No bienes found.");
            return;
        }

        console.printf("%n  Total: %d bien(es)%n", bienes.size());
        bienes.forEach(this::print);
    }
}