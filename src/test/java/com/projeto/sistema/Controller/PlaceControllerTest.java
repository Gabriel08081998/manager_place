package com.projeto.sistema.Controller;

import com.github.slugify.Slugify;
import com.projeto.sistema.controller.PlaceController;
import com.projeto.sistema.model.Place;
import com.projeto.sistema.repository.PlaceRepository;
import com.projeto.sistema.service.PlaceService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)

class PlaceControllerTest {
    @InjectMocks
    private PlaceController placeController;
    @Mock
    private PlaceService placeService;

    private PlaceDTO placeDTO;
    private Place place;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Start();
    }

    @Test
    void createPlace() {
        when(placeService.createPlace(any())).thenReturn(Optional.of(place));
        ResponseEntity<?> response = placeController.createPlace(placeDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    @Test
    void createPlaceException() {
        when(placeService.createPlace(any())).thenReturn(Optional.empty());
        ResponseEntity<?> response = placeController.createPlace(placeDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    private void Start() {
        place = new Place(1l,"teste", "teste", "teste", LocalDateTime.now(), LocalDateTime.now());
        placeDTO = new PlaceDTO();
        BeanUtils.copyProperties(place, placeDTO);
        Slugify slugify = new Slugify();
        placeDTO.setSlug(slugify.slugify(place.getName()));

    }
}