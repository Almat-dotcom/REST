package kz.bitlab.rest_api.service.impl;

import kz.bitlab.rest_api.dto.*;
import kz.bitlab.rest_api.entity.Country;
import kz.bitlab.rest_api.entity.Tyre;
import kz.bitlab.rest_api.mapper.TyreMapper;
import kz.bitlab.rest_api.repository.CountryRepository;
import kz.bitlab.rest_api.repository.TyreRepository;
import kz.bitlab.rest_api.service.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
        Country country = countryRepository.findById(dto.getCountryId()).orElse(null);
        Tyre tyre = Tyre.builder()
                .name(dto.getNames())
                .profile(dto.getProfile())
                .manufacturer(dto.getManufacturer())
                .price(dto.getPrice())
                .build();
        if (country != null) {
            tyre.setCountry(country);
        }
        Tyre saved = tyreRepository.save(tyre);
        return TyreCreateResultDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .build();
    }

    @Override
    public TyreFullDTO getTyre(Long id) {
        Tyre tyre = tyreRepository.findById(id).orElse(null);
        if (tyre == null) return null;
        CountryFullDTO countryDto = CountryFullDTO.builder()
                .id(tyre.getCountry().getId())
                .name(tyre.getCountry().getName())
                .build();

        TyreFullDTO result = TyreFullDTO.builder()
                .id(tyre.getId())
                .name(tyre.getName())
                .profile(tyre.getProfile())
                .price(tyre.getPrice())
                .manufacturer(tyre.getManufacturer())
                .country(countryDto)
                .build();
        return result;
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

        CountryFullDTO countryDto = CountryFullDTO.builder()
                .id(saved.getCountry().getId())
                .name(saved.getCountry().getName())
                .build();

        TyreFullDTO result = TyreFullDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .profile(saved.getProfile())
                .price(saved.getPrice())
                .manufacturer(saved.getManufacturer())
                .country(countryDto)
                .build();
        return result;
    }

    @Override
    public boolean deleteTyre(Long id) {
        tyreRepository.deleteById(id);
        return true;
    }
}
