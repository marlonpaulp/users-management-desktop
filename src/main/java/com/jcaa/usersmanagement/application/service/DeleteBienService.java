package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteBienUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteBienPort;
import com.jcaa.usersmanagement.application.port.out.GetBienByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteBienCommand;
import com.jcaa.usersmanagement.application.service.mapper.BienApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.BienNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteBienService implements DeleteBienUseCase {

    private final GetBienByIdPort getBienByIdPort;
    private final DeleteBienPort deleteBienPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteBienCommand command) {
        validateCommand(command);

        final BienId bienId =
                BienApplicationMapper.fromDeleteCommandToBienId(command);

        getBienByIdPort.getById(bienId)
                .orElseThrow(() ->
                        BienNotFoundException.becauseIdWasNotFound(bienId.value()));

        deleteBienPort.delete(bienId);
    }

    private void validateCommand(final DeleteBienCommand command) {
        final Set<ConstraintViolation<DeleteBienCommand>> violations =
                validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
