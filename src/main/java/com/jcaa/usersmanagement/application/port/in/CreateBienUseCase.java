package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateBienCommand;
import com.jcaa.usersmanagement.domain.model.Bien;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateBienUseCase {
    Bien execute(@NotNull @Valid CreateBienCommand command);
}