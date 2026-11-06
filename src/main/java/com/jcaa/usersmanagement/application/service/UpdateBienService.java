package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateBienUseCase;
import com.jcaa.usersmanagement.application.port.out.GetBienByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateBienPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateBienCommand;
import com.jcaa.usersmanagement.application.service.mapper.BienApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.BienNotFoundException;
import com.jcaa.usersmanagement.domain.model.Bien;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UpdateBienService implements UpdateBienUseCase {

    private final GetBienByIdPort getBienByIdPort;
    private final UpdateBienPort updateBienPort;
    private final Validator validator;

    @Override
    public Bien execute(final UpdateBienCommand command) {
        validateCommand(command);

        final BienId bienId = new BienId(command.id());

        getBienByIdPort.getById(bienId)
                .orElseThrow(() ->
                        BienNotFoundException.becauseIdWasNotFound(bienId.value()));

        final Bien bienToUpdate =
                BienApplicationMapper.fromUpdateCommandToModel(command);

        return updateBienPort.update(bienToUpdate);
    }

    private void validateCommand(final UpdateBienCommand command) {
        final Set<ConstraintViolation<UpdateBienCommand>> violations =
                validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
