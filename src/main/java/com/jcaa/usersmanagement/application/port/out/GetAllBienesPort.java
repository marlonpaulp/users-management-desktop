package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.Bien;
import java.util.List;

public interface GetAllBienesPort {
    List<Bien> getAll();
}