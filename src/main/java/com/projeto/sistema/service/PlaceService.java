package com.projeto.sistema.service;

import com.projeto.sistema.model.Place;
import com.projeto.sistema.view.PlaceDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface PlaceService {

    public Optional<Place> createPlace(PlaceDTO placeDTO);

    Optional<Place> save(Place place);
}
