package kz.bitlab.rest_api.service;

import kz.bitlab.rest_api.entity.Tyre;

import java.util.List;

public interface TyreService {
    List<Tyre> getTyres();

    Tyre addTyre(Tyre tyre);

    Tyre getTyre(Long id);

    Tyre updateTyre(Long id, Tyre tyre);

    boolean deleteTyre(Long id);
}
