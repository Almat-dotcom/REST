package kz.bitlab.rest_api.services.impl;

import kz.bitlab.rest_api.dto.TyreRequestDTO;
import kz.bitlab.rest_api.dto.TyreResponseDTO;
import kz.bitlab.rest_api.entity.Tyre;
import kz.bitlab.rest_api.entity.TyreCategory;
import kz.bitlab.rest_api.mapper.TyreMapper;
import kz.bitlab.rest_api.repositories.TyreCategoryRepository;
import kz.bitlab.rest_api.repositories.TyreRepository;
import kz.bitlab.rest_api.services.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TyreServiceImpl implements TyreService {
    private final TyreRepository tyreRepository;
    private final TyreCategoryRepository tyreCategoryRepository;
    private final TyreMapper tyreMapper;

    @Override
    public List<TyreResponseDTO> getAll() {
        List<Tyre> tyres = tyreRepository.findAll();
        List<TyreResponseDTO> tyreResponseDTOS = new ArrayList<>();
//        return tyres.stream().map(t -> mapToDTO(t))
//                .toList();
        for (Tyre t : tyres) {
            TyreResponseDTO dto = tyreMapper.mapToDTO(t);
            tyreResponseDTOS.add(dto);
        }
        return tyreResponseDTOS;
    }

    @Override
    public Tyre getById(Long id) {
        return tyreRepository.findById(id).orElse(null);
    }

    @Override
    public Tyre create(TyreRequestDTO dto) {
        Tyre tyre = tyreMapper.mapToEntity(dto);
        return tyreRepository.save(tyre);
    }

    @Override
    public Tyre update(Tyre tyre) {
        return tyreRepository.save(tyre);
    }

    @Override
    public Tyre update(Long id, String name) {
        Tyre tyre = tyreRepository.findById(id).orElse(null);
        if (tyre != null) {
            tyre.setTyreName(name);
            tyreRepository.save(tyre);
        }
        return tyre;
    }

    @Override
    public void delete(Long id) {
        tyreRepository.deleteById(id);
    }

//    private TyreResponseDTO mapToDTO(Tyre tyre) {
//        List<String> categoryNames = new ArrayList<>();
////        for (TyreCategory c : tyre.getCategories()) {
////            categoryNames.add(c.getName());
////        }
//        return TyreResponseDTO.builder()
//                .id(tyre.getId())
//                .name(tyre.getTyreName())
//                .price(tyre.getPrice())
//                .profile(tyre.getProfile())
//                .manufacturer(tyre.getManufacturer())
//                .build();
//    }

//    private Tyre mapToEntity(TyreRequestDTO dto) {
//        List<TyreCategory> categories = new ArrayList<>();
//        for (Long id : dto.getCategoriesId()) {
//            categories.add(tyreCategoryRepository.findById(id).orElse(null));
//        }
//        Tyre tyre = new Tyre();
//        tyre.setTyreName(dto.getName());
//        tyre.setProfile(dto.getProfile());
//        tyre.setPrice(dto.getPrice());
//        tyre.setManufacturer(dto.getManufacturer());
//        tyre.setCategories(categories);
//        return tyre;
//    }
}
