package kz.bitlab.rest_api.controller;

import kz.bitlab.rest_api.dto.*;
import kz.bitlab.rest_api.entity.Tyre;
import kz.bitlab.rest_api.service.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tyres")
public class TyreController {
    private final TyreService tyreService;

    @GetMapping()
    public ResponseEntity<List<TyreListDTO>> getAll() {
        return new ResponseEntity<>(tyreService.getTyres(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TyreFullDTO> getTyre(@PathVariable Long id) {
        return new ResponseEntity<>(tyreService.getTyre(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TyreCreateResultDTO> createTyre(@RequestBody TyreCreateDTO dto) {
        TyreCreateResultDTO result = tyreService.addTyre(dto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTyre(@PathVariable Long id) {
        tyreService.deleteTyre(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<TyreFullDTO> updateTyre(@RequestBody TyreUpdateDTO tyre) {
        return new ResponseEntity<>(tyreService.updateTyre(tyre.getId(), tyre), HttpStatus.OK);
    }
}

