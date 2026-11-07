package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.domain.model.Bien;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import com.jcaa.usersmanagement.domain.valueobject.CodigoBien;
import com.jcaa.usersmanagement.domain.valueobject.NombreBien;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("BienRepositoryMySQL")
@ExtendWith(MockitoExtension.class)
class BienRepositoryMySQLTest {

    private static final String ID = "b-001";
    private static final String CODIGO = "INV-001";
    private static final String NOMBRE = "Computador";
    private static final String DESCRIPCION = "Computador institucional";
    private static final String TIPO = "Equipo tecnológico";
    private static final String UNIDAD_MEDIDA = "Unidad";
    private static final BigDecimal VALOR = new BigDecimal("2500000.00");
    private static final String ESTADO = "DISPONIBLE";

    @Mock private Connection connection;
    @Mock private PreparedStatement statement;
    @Mock private ResultSet resultSet;

    private BienRepositoryMySQL repository;
    private Bien bien;
    private BienId bienId;

    @BeforeEach
    void setUp() {
        repository = new BienRepositoryMySQL(connection);
        bienId = new BienId(ID);

        bien = Bien.create(
                bienId,
                new CodigoBien(CODIGO),
                new NombreBien(NOMBRE),
                DESCRIPCION,
                TIPO,
                UNIDAD_MEDIDA,
                VALOR);
    }

    private void configureStatement() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
    }

    private void configureResultSetRow() throws SQLException {
        when(resultSet.getString("id")).thenReturn(ID);
        when(resultSet.getString("codigo")).thenReturn(CODIGO);
        when(resultSet.getString("nombre")).thenReturn(NOMBRE);
        when(resultSet.getString("descripcion")).thenReturn(DESCRIPCION);
        when(resultSet.getString("tipo")).thenReturn(TIPO);
        when(resultSet.getString("unidad_medida")).thenReturn(UNIDAD_MEDIDA);
        when(resultSet.getBigDecimal("valor_unitario")).thenReturn(VALOR);
        when(resultSet.getString("estado")).thenReturn(ESTADO);
    }

    @Test
    @DisplayName("save() inserts a Bien and returns the saved object")
    void shouldSaveBien() throws SQLException {
        configureStatement();
        when(statement.executeUpdate()).thenReturn(1);

        final Bien result = repository.save(bien);

        assertSame(bien, result);
        verify(statement).executeUpdate();
    }

    @Test
    @DisplayName("save() throws PersistenceException when INSERT fails")
    void shouldThrowExceptionWhenSaveFails() throws SQLException {
        configureStatement();
        when(statement.executeUpdate()).thenThrow(new SQLException("Insert failed"));

        assertThrows(PersistenceException.class, () -> repository.save(bien));
    }

    @Test
    @DisplayName("update() updates a Bien and returns the updated object")
    void shouldUpdateBien() throws SQLException {
        configureStatement();
        when(statement.executeUpdate()).thenReturn(1);

        final Bien result = repository.update(bien);

        assertSame(bien, result);
        verify(statement).executeUpdate();
    }

    @Test
    @DisplayName("update() throws IllegalStateException when no row is updated")
    void shouldThrowExceptionWhenBienDoesNotExistDuringUpdate() throws SQLException {
        configureStatement();
        when(statement.executeUpdate()).thenReturn(0);

        assertThrows(IllegalStateException.class, () -> repository.update(bien));
    }

    @Test
    @DisplayName("update() throws PersistenceException when UPDATE fails")
    void shouldThrowExceptionWhenUpdateFails() throws SQLException {
        configureStatement();
        when(statement.executeUpdate()).thenThrow(new SQLException("Update failed"));

        assertThrows(PersistenceException.class, () -> repository.update(bien));
    }

    @Test
    @DisplayName("getById() returns a Bien when a matching row exists")
    void shouldReturnBienWhenFound() throws SQLException {
        configureStatement();
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        configureResultSetRow();

        final Optional<Bien> result = repository.getById(bienId);

        assertAll(
                () -> assertTrue(result.isPresent()),
                () -> assertEquals(ID, result.get().getId().value()),
                () -> assertEquals(NOMBRE, result.get().getNombre().value()));
    }

    @Test
    @DisplayName("getById() returns Optional.empty() when no row exists")
    void shouldReturnEmptyWhenBienIsNotFound() throws SQLException {
        configureStatement();
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        final Optional<Bien> result = repository.getById(bienId);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("getById() throws PersistenceException when query fails")
    void shouldThrowExceptionWhenGetByIdFails() throws SQLException {
        configureStatement();
        when(statement.executeQuery()).thenThrow(new SQLException("Query failed"));

        assertThrows(PersistenceException.class, () -> repository.getById(bienId));
    }

    @Test
    @DisplayName("getAll() returns all Bien records")
    void shouldReturnAllBienes() throws SQLException {
        configureStatement();
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getString("id")).thenReturn(ID, "b-002");
        when(resultSet.getString("codigo")).thenReturn(CODIGO, "INV-002");
        when(resultSet.getString("nombre")).thenReturn(NOMBRE, "Impresora");
        when(resultSet.getString("descripcion")).thenReturn(DESCRIPCION, "Impresora institucional");
        when(resultSet.getString("tipo")).thenReturn(TIPO, TIPO);
        when(resultSet.getString("unidad_medida")).thenReturn(UNIDAD_MEDIDA, UNIDAD_MEDIDA);
        when(resultSet.getBigDecimal("valor_unitario")).thenReturn(VALOR, new BigDecimal("900000.00"));
        when(resultSet.getString("estado")).thenReturn(ESTADO, ESTADO);

        final List<Bien> result = repository.getAll();

        assertAll(
                () -> assertEquals(2, result.size()),
                () -> assertEquals(ID, result.get(0).getId().value()),
                () -> assertEquals("b-002", result.get(1).getId().value()));
    }

    @Test
    @DisplayName("getAll() throws PersistenceException when query fails")
    void shouldThrowExceptionWhenGetAllFails() throws SQLException {
        configureStatement();
        when(statement.executeQuery()).thenThrow(new SQLException("Query failed"));

        assertThrows(PersistenceException.class, () -> repository.getAll());
    }

    @Test
    @DisplayName("delete() executes DELETE successfully")
    void shouldDeleteBien() throws SQLException {
        configureStatement();
        when(statement.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(() -> repository.delete(bienId));

        verify(statement).executeUpdate();
    }

    @Test
    @DisplayName("delete() throws PersistenceException when DELETE fails")
    void shouldThrowExceptionWhenDeleteFails() throws SQLException {
        configureStatement();
        when(statement.executeUpdate()).thenThrow(new SQLException("Delete failed"));

        assertThrows(PersistenceException.class, () -> repository.delete(bienId));
    }
}