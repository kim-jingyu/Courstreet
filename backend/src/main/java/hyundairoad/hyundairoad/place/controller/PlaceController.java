package hyundairoad.hyundairoad.place.controller;

import hyundairoad.hyundairoad.place.domain.PlaceResponse;
import hyundairoad.hyundairoad.place.domain.dto.CreatePlaceRequest;
import hyundairoad.hyundairoad.place.domain.dto.UpdatePlaceRequest;
import hyundairoad.hyundairoad.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping("/all")
    public ResponseEntity<List<PlaceResponse>> getPlaceList() throws MalformedURLException {
        return ResponseEntity.ok().body(placeService.getAllPlaces());
    }

    @GetMapping
    public ResponseEntity<List<PlaceResponse>> getPlaceSearchedList(@RequestBody String name) throws MalformedURLException {
        return ResponseEntity.ok().body(placeService.getSearchedPlaces(name));
    }

    @PostMapping
    public ResponseEntity<Void> createPlace(@ModelAttribute CreatePlaceRequest createPlaceRequest) throws IOException {
        return ResponseEntity.ok().body(placeService.createPlace(createPlaceRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updatePlace(@ModelAttribute UpdatePlaceRequest updatePlaceRequest) throws IOException {
        return ResponseEntity.ok().body(placeService.updatePlace(updatePlaceRequest));
    }
}
