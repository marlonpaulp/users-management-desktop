package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateBienUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveBienPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateBienCommand;
import com.jcaa.usersmanagement.application.service.mapper.BienApplicationMapper;
import com.jcaa.usersmanagement.domain.model.Bien;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CreateBienService implements CreateBienUseCase {

    private final SaveBienPort saveBienPort;
    private final Validator validator;

    @Override
    public Bien execute(final CreateBienCommand command) {
        validateCommand(command);

        final Bien bienToSave =
                BienApplicationMapper.fromCreateCommandToModel(command);

        return saveBienPort.save(bienToSave);
    }

    private void validateCommand(final CreateBienCommand command) {
        final Set<ConstraintViolation<CreateBienCommand>> violations =
                validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
