package com.hyundairoad.hyundairoad.place.service.impl;

import com.hyundairoad.hyundairoad.place.domain.Place;
import com.hyundairoad.hyundairoad.place.mapper.PlaceMapper;
import com.hyundairoad.hyundairoad.place.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
@AllArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper mapper;


    @Override
    public List<Place> getList() {
        return mapper.getList();
    }

    @Override
    public void register(Place place) {
        System.out.println(place);
//        mapper.insert();
    }
}
