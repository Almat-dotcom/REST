package kz.bitlab.rest_api.service.impl;

import kz.bitlab.rest_api.entity.Tyre;
import kz.bitlab.rest_api.repository.TyreRepository;
import kz.bitlab.rest_api.service.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TyreServiceImpl implements TyreService {
    private final TyreRepository tyreRepository;

    @Override
    public List<Tyre> getTyres() {
        return tyreRepository.findAll();
    }

    @Override
    public Tyre addTyre(Tyre tyre) {
        return tyreRepository.save(tyre);
    }

    @Override
    public Tyre getTyre(Long id) {
        return tyreRepository.findById(id).orElse(null);
    }

    @Override
    public Tyre updateTyre(Long id, Tyre tyre) {
        Tyre oldTyre = tyreRepository.findById(id).orElse(null);
        if (oldTyre == null)
            return null;
        oldTyre.setName(tyre.getName());
        oldTyre.setProfile(tyre.getProfile());
        oldTyre.setPrice(tyre.getPrice());
        oldTyre.setManufacturer(tyre.getManufacturer());
        return tyreRepository.save(oldTyre);
    }

    @Override
    public boolean deleteTyre(Long id) {
        tyreRepository.deleteById(id);
        return true;
    }
}
