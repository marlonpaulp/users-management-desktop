package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetBienByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetBienByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetBienByIdQuery;
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
public final class GetBienByIdService implements GetBienByIdUseCase {

    private final GetBienByIdPort getBienByIdPort;
    private final Validator validator;

    @Override
    public Bien execute(final GetBienByIdQuery query) {
        validateQuery(query);

        final BienId bienId =
                BienApplicationMapper.fromGetBienByIdQueryToBienId(query);

        return getBienByIdPort.getById(bienId)
                .orElseThrow(() ->
                        BienNotFoundException.becauseIdWasNotFound(bienId.value()));
    }

    private void validateQuery(final GetBienByIdQuery query) {
        final Set<ConstraintViolation<GetBienByIdQuery>> violations =
                validator.validate(query);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
