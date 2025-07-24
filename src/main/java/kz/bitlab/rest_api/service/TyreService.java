package kz.bitlab.rest_api.service;

import kz.bitlab.rest_api.dto.*;
import kz.bitlab.rest_api.entity.Tyre;

import java.util.List;

public interface TyreService {
    List<TyreListDTO> getTyres();

    TyreCreateResultDTO addTyre(TyreCreateDTO dto);

    TyreFullDTO getTyre(Long id);

    TyreFullDTO updateTyre(Long id, TyreUpdateDTO tyre);

    boolean deleteTyre(Long id);
}
