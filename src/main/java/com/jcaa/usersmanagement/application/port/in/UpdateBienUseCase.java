package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateBienCommand;
import com.jcaa.usersmanagement.domain.model.Bien;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateBienUseCase {
    Bien execute(@NotNull @Valid UpdateBienCommand command);
}