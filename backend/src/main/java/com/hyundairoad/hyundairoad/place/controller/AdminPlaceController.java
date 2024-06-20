package com.hyundairoad.hyundairoad.place.controller;

import com.hyundairoad.hyundairoad.place.domain.Place;
import com.hyundairoad.hyundairoad.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping("/admin/place")
@RequiredArgsConstructor
public class AdminPlaceController {

    private final PlaceService service;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", service.getList());
        return "admin/place/list";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Place place, @RequestParam("time1") String startTime,
                           @RequestParam("time2") String endTime) {
        // LocalTime 변환 후 설정
        LocalTime startLocalTime = LocalTime.parse(startTime);
        LocalTime endLocalTime = LocalTime.parse(endTime);

        LocalDate currentDate = LocalDate.now();
        LocalDateTime startDateTime = LocalDateTime.of(currentDate, startLocalTime);
        LocalDateTime endDateTime = LocalDateTime.of(currentDate, endLocalTime);

        place.setStartTime(startDateTime);
        place.setEndTime(endDateTime);

        service.register(place);

        return "redirect:/admin/place/list";
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            service.remove(id);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Place place) {
        try {
            service.update(place);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPlaceById(@PathVariable Long id) {
        Place place = service.getPlaceByPlaceId(id);
        if (place != null) {
            return ResponseEntity.ok(place);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Place not found");
        }
    }
}
