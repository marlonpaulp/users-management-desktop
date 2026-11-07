package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteBienPort;
import com.jcaa.usersmanagement.application.port.out.GetAllBienesPort;
import com.jcaa.usersmanagement.application.port.out.GetBienByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveBienPort;
import com.jcaa.usersmanagement.application.port.out.UpdateBienPort;
import com.jcaa.usersmanagement.domain.model.Bien;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.BienPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.BienPersistenceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class BienRepositoryMySQL
        implements SaveBienPort,
        UpdateBienPort,
        GetBienByIdPort,
        GetAllBienesPort,
        DeleteBienPort {

    private static final String SQL_INSERT =
            "INSERT INTO bienes "
            + "(id, codigo, nombre, descripcion, tipo, unidad_medida, valor_unitario, estado) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE bienes SET codigo = ?, nombre = ?, descripcion = ?, "
            + "tipo = ?, unidad_medida = ?, valor_unitario = ?, estado = ? "
            + "WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, codigo, nombre, descripcion, tipo, unidad_medida, "
            + "valor_unitario, estado FROM bienes WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id, codigo, nombre, descripcion, tipo, unidad_medida, "
            + "valor_unitario, estado FROM bienes ORDER BY nombre ASC";

    private static final String SQL_DELETE =
            "DELETE FROM bienes WHERE id = ?";

    private final Connection connection;

    @Override
    public Bien save(final Bien bien) {
        final BienPersistenceDto dto = BienPersistenceMapper.fromModelToDto(bien);

        try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            setBienParameters(statement, dto);
            statement.executeUpdate();
            return bien;
        } catch (final SQLException exception) {
            throw PersistenceException.becauseSaveBienFailed(dto.id(), exception);
        }
    }

    @Override
    public Bien update(final Bien bien) {
        final BienPersistenceDto dto = BienPersistenceMapper.fromModelToDto(bien);

        try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, dto.codigo());
            statement.setString(2, dto.nombre());
            statement.setString(3, dto.descripcion());
            statement.setString(4, dto.tipo());
            statement.setString(5, dto.unidadMedida());
            statement.setBigDecimal(6, dto.valorUnitario());
            statement.setString(7, dto.estado());
            statement.setString(8, dto.id());

            if (statement.executeUpdate() == 0) {
                throw new IllegalStateException("No existe un bien con ID: " + dto.id());
            }

            return bien;
        } catch (final SQLException exception) {
            throw PersistenceException.becauseUpdateBienFailed(dto.id(), exception);
        }
    }

    @Override
    public Optional<Bien> getById(final BienId bienId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, bienId.value());

            try (final ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }

                return Optional.of(mapResultSet(resultSet));
            }
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindBienByIdFailed(
                    bienId.value(), exception);
        }
    }

    @Override
    public List<Bien> getAll() {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL);
             final ResultSet resultSet = statement.executeQuery()) {

            final List<BienPersistenceDto> dtos = new ArrayList<>();

            while (resultSet.next()) {
                dtos.add(mapResultSetToDto(resultSet));
            }

            return BienPersistenceMapper.fromDtoListToModelList(dtos);
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindAllBienesFailed(exception);
        }
    }

    @Override
    public void delete(final BienId bienId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, bienId.value());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseDeleteBienFailed(
                    bienId.value(), exception);
        }
    }

    private void setBienParameters(
            final PreparedStatement statement,
            final BienPersistenceDto dto) throws SQLException {

        statement.setString(1, dto.id());
        statement.setString(2, dto.codigo());
        statement.setString(3, dto.nombre());
        statement.setString(4, dto.descripcion());
        statement.setString(5, dto.tipo());
        statement.setString(6, dto.unidadMedida());
        statement.setBigDecimal(7, dto.valorUnitario());
        statement.setString(8, dto.estado());
    }

    private Bien mapResultSet(final ResultSet resultSet) throws SQLException {
        return BienPersistenceMapper.fromDtoToModel(mapResultSetToDto(resultSet));
    }

    private BienPersistenceDto mapResultSetToDto(final ResultSet resultSet)
            throws SQLException {

        return new BienPersistenceDto(
                resultSet.getString("id"),
                resultSet.getString("codigo"),
                resultSet.getString("nombre"),
                resultSet.getString("descripcion"),
                resultSet.getString("tipo"),
                resultSet.getString("unidad_medida"),
                resultSet.getBigDecimal("valor_unitario"),
                resultSet.getString("estado"));
    }
}
