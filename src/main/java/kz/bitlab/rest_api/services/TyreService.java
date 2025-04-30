package kz.bitlab.rest_api.services;

import kz.bitlab.rest_api.dto.TyreRequestDTO;
import kz.bitlab.rest_api.dto.TyreResponseDTO;
import kz.bitlab.rest_api.entity.Tyre;

import java.util.List;

public interface TyreService {
    List<TyreResponseDTO> getAll();

    Tyre getById(Long id);

    Tyre create(TyreRequestDTO dto);

    Tyre update(Tyre tyre);

    Tyre update(Long id, String name);

    void delete(Long id);
}
