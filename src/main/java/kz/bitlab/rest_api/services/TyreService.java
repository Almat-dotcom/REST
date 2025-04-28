package kz.bitlab.rest_api.services;

import kz.bitlab.rest_api.entity.Tyre;

import java.util.List;

public interface TyreService {
    List<Tyre> getAll();

    Tyre getById(Long id);

    Tyre create(Tyre tyre);

    Tyre update(Tyre tyre);

    Tyre update(Long id, String name);

    void delete(Long id);
}
