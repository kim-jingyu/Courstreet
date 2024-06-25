package com.hyundairoad.place.repository;

import com.hyundairoad.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByNameContaining(String name);
}
