package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllBienesUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllBienesPort;
import com.jcaa.usersmanagement.domain.model.Bien;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class GetAllBienesService implements GetAllBienesUseCase {

    private final GetAllBienesPort getAllBienesPort;

    @Override
    public List<Bien> execute() {
        return getAllBienesPort.getAll();
    }
}
