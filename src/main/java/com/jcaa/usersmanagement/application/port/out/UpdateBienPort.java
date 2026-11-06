package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Bien;

public interface UpdateBienPort {
    Bien update(Bien bien);
}