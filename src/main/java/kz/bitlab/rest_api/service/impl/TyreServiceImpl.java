package kz.bitlab.rest_api.service.impl;

import jakarta.transaction.Transactional;
import kz.bitlab.rest_api.dto.*;
import kz.bitlab.rest_api.entity.Tyre;
import kz.bitlab.rest_api.mapper.TyreMapper;
import kz.bitlab.rest_api.repository.CountryRepository;
import kz.bitlab.rest_api.repository.TyreRepository;
import kz.bitlab.rest_api.service.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TyreServiceImpl implements TyreService {
    private final TyreRepository tyreRepository;
    private final CountryRepository countryRepository;
    private final TyreMapper tyreMapper;

    @Override
    public List<TyreListDTO> getTyres() {
        List<Tyre> tyres = tyreRepository.findAll();
        return tyreMapper.toTyreList(tyres);
    }

    @Override
    public TyreCreateResultDTO addTyre(TyreCreateDTO dto) {
        Tyre tyre = tyreMapper.toTyre(dto);
        Tyre saved = tyreRepository.save(tyre);
        return tyreMapper.toCreateDTO(saved);
    }

    @Override
    public TyreFullDTO getTyre(Long id) {
        Tyre tyre = tyreRepository.findById(id).orElse(null);

        return tyreMapper.toFullDTO(tyre);
    }

    @Override
    public TyreFullDTO updateTyre(Long id, TyreUpdateDTO tyre) {
        Tyre oldTyre = tyreRepository.findById(id).orElse(null);
        if (oldTyre == null)
            return null;
        oldTyre.setName(tyre.getName());
        oldTyre.setProfile(tyre.getProfile());
        oldTyre.setPrice(tyre.getPrice());
        oldTyre.setManufacturer(tyre.getManufacturer());
        Tyre saved = tyreRepository.save(oldTyre);

        return tyreMapper.toFullDTO(saved);
    }

    @Override
    public boolean deleteTyre(Long id) {
        tyreRepository.deleteById(id);
        return true;
    }
}
