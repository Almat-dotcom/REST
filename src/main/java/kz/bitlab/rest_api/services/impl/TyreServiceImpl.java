package kz.bitlab.rest_api.services.impl;

import kz.bitlab.rest_api.entity.Tyre;
import kz.bitlab.rest_api.repositories.TyreRepository;
import kz.bitlab.rest_api.services.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TyreServiceImpl implements TyreService {
    private final TyreRepository tyreRepository;

    @Override
    public List<Tyre> getAll() {
        return tyreRepository.findAll();
    }

    @Override
    public Tyre getById(Long id) {
        return tyreRepository.findById(id).orElse(null);
    }

    @Override
    public Tyre create(Tyre tyre) {
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
            tyre.setName(name);
            tyreRepository.save(tyre);
        }
        return tyre;
    }

    @Override
    public void delete(Long id) {
        tyreRepository.deleteById(id);
    }
}
