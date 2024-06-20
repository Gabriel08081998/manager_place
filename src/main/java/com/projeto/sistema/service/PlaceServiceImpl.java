package com.projeto.sistema.service;


import com.github.slugify.Slugify;
import com.projeto.sistema.model.Place;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.sistema.repository.PlaceRepository;
import com.projeto.sistema.view.PlaceDTO;

import java.util.Optional;
@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceRepository placeRepository;


    @Override
    public Optional<Place> createPlace(PlaceDTO placeDTO) {

        if (placeRepository.existsByName(placeDTO.getName())) {
            throw new RuntimeException("Place name exists");
        }
        Place place = Place.builder().build();
        BeanUtils.copyProperties(placeDTO, place);

        Slugify slugify = new Slugify();
        place.setSlug(slugify.slugify(place.getName()));

        return Optional.of(placeRepository.save(place));
    }

    @Override
    public Optional<Place> save(Place place) {
        return Optional.of(placeRepository.save(place));
    }
}
