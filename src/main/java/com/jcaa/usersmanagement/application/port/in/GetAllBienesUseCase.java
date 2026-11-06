package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.Bien;
import java.util.List;

public interface GetAllBienesUseCase {
    List<Bien> execute();
}