package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Bien;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import java.util.Optional;

public interface GetBienByIdPort {
    Optional<Bien> getById(BienId bienId);
}