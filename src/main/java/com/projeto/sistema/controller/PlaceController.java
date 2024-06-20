package com.projeto.sistema.controller;

import com.projeto.sistema.model.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.projeto.sistema.repository.PlaceRepository;
import com.projeto.sistema.view.PlaceDTO;
import com.projeto.sistema.service.PlaceService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceRepository placeRepository;

    @PostMapping
    public ResponseEntity<?> createPlace(@RequestBody @Valid PlaceDTO placeDTO) {
        try{
            Optional<Place> optionalPlace = placeService.createPlace(placeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalPlace.get());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
