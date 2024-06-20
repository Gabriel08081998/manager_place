package com.projeto.sistema.repository;

import com.projeto.sistema.model.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlaceRepository extends CrudRepository<Place, Long> {
    boolean existsByName(String name);
}
