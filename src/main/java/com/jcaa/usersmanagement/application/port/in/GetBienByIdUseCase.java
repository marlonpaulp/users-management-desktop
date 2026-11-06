package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetBienByIdQuery;
import com.jcaa.usersmanagement.domain.model.Bien;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetBienByIdUseCase {
    Bien execute(@NotNull @Valid GetBienByIdQuery query);
}