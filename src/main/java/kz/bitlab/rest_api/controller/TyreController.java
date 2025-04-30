package kz.bitlab.rest_api.controller;

import kz.bitlab.rest_api.dto.TyreRequestDTO;
import kz.bitlab.rest_api.dto.TyreResponseDTO;
import kz.bitlab.rest_api.entity.Tyre;
import kz.bitlab.rest_api.services.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/tyres")
@RequiredArgsConstructor
public class TyreController {
    private final TyreService tyreService;

    @GetMapping
    public ResponseEntity<List<TyreResponseDTO>> main(Model model) {
        if (tyreService.getAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(tyreService.getAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tyre> getTyreById(@PathVariable Long id) {
        Tyre tyre = tyreService.getById(id);
        if(tyre == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
        return new ResponseEntity<>(tyre, HttpStatus.OK);
        }

    }

    @PostMapping
    public ResponseEntity<?> createTyre(@RequestBody TyreRequestDTO dto) {
        if(dto==null){
            throw new RuntimeException("Tyre is null");
        }
        else {
            tyreService.create(dto);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }
    }

    @PutMapping
    public ResponseEntity<?> updateTyre(@RequestBody Tyre tyre) {
        if(tyre==null){
            throw new RuntimeException("Tyre is null");
        }else {
            tyreService.update(tyre);
            return ResponseEntity.ok().body(tyre);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTyre(@PathVariable Long id, @RequestParam(name = "name") String name) {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Tyre name is null or empty");
        }else {
            if(tyreService.getById(id)==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            else
            tyreService.update(id, name);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTyre(@PathVariable Long id) {
        if(tyreService.getById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            tyreService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
