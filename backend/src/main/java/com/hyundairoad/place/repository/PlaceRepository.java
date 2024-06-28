package com.hyundairoad.place.repository;

import com.hyundairoad.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * PlaceRepository
 *
 * 작성자: 김진규
 * 작성일: 2024-06-29
 */
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByNameContaining(String name);
}
