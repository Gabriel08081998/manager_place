package com.projeto.sistema.service;

import com.github.slugify.Slugify;
import com.projeto.sistema.model.Place;
import com.projeto.sistema.repository.PlaceRepository;
import com.projeto.sistema.view.PlaceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PlaceServiceImplTest {
    @InjectMocks
    private PlaceServiceImpl placeService;

    @Mock
    private PlaceRepository placeRepository;

    private PlaceDTO placeDTO;
     private Place place;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Start();
    }

    @Test
    void createPlace() {
        Start();
        when(placeRepository.save(any(Place.class))).thenReturn(place);
        when(placeRepository.existsByName(anyString())).thenReturn(false);
        placeService.createPlace(placeDTO);

    }

    @Test
    void createPlaceException() {
        Start();
        when(placeRepository.existsByName(anyString())).thenReturn(true);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            placeService.createPlace(placeDTO);
        });
        assertEquals("Place name exists",exception.getMessage());


    }

    @Test
    void save() {
        Place place1 = new Place();
        place1.setId(1l);
        place1.setName("teste");
        place1.setSlug("teste");
        place1.setCreatedAt(LocalDateTime.now());
        place1.setUpdatedAt(LocalDateTime.now());
        when(placeRepository.save(any(Place.class))).thenReturn(place1);

        Optional<Place> place = placeService.save(place1);
        assertTrue(place.isPresent());

        assertEquals(place1, place.get());


    }
    private void Start() {
        place = new Place(1l,"teste", "teste", "teste", LocalDateTime.now(), LocalDateTime.now());
        placeDTO = new PlaceDTO();
        BeanUtils.copyProperties(place, placeDTO);
        Slugify slugify = new Slugify();
        placeDTO.setSlug(slugify.slugify(place.getName()));

    }

}