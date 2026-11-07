package com.jcaa.usersmanagement.infrastructure.adapter.memory;

import com.jcaa.usersmanagement.application.port.out.DeleteBienPort;
import com.jcaa.usersmanagement.application.port.out.GetAllBienesPort;
import com.jcaa.usersmanagement.application.port.out.GetBienByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveBienPort;
import com.jcaa.usersmanagement.application.port.out.UpdateBienPort;
import com.jcaa.usersmanagement.domain.model.Bien;
import com.jcaa.usersmanagement.domain.valueobject.BienId;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BienesMemoria implements
        SaveBienPort,
        GetBienByIdPort,
        GetAllBienesPort,
        UpdateBienPort,
        DeleteBienPort {

    private final ConcurrentMap<String, Bien> bienes = new ConcurrentHashMap<>();

    @Override
    public Bien save(final Bien bien) {
        final Bien previous = bienes.putIfAbsent(
                bien.getId().value(), bien);

        if (previous != null) {
            throw new IllegalStateException(
                    "Ya existe un bien con ID: " + bien.getId().value());
        }

        return bien;
    }

    @Override
    public Optional<Bien> getById(final BienId bienId) {
        return Optional.ofNullable(bienes.get(bienId.value()));
    }

    @Override
    public List<Bien> getAll() {
        return List.copyOf(bienes.values());
    }

    @Override
    public Bien update(final Bien bien) {
        final String id = bien.getId().value();

        if (!bienes.containsKey(id)) {
            throw new IllegalStateException(
                    "No existe un bien con ID: " + id);
        }

        bienes.put(id, bien);
        return bien;
    }

    @Override
    public void delete(final BienId bienId) {
        bienes.remove(bienId.value());
    }
}