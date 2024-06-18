package com.hyundairoad.hyundairoad.place.controller;

import com.hyundairoad.hyundairoad.place.domain.Place;
import com.hyundairoad.hyundairoad.place.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Log
@Controller
@RequestMapping("/place/*")
@AllArgsConstructor
public class PlaceController {

    private PlaceService service;

    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", service.getList());
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Place place, @RequestParam("time1") String startTime,
                           @RequestParam("time2") String endTime) {
        // LocalTime 변환 후 set
        LocalTime startLocalTime = LocalTime.parse(startTime);
        LocalTime endLocalTime = LocalTime.parse(endTime);

        LocalDate currentDate = LocalDate.now();
        LocalDateTime startDateTime = LocalDateTime.of(currentDate, startLocalTime);
        LocalDateTime endDateTime = LocalDateTime.of(currentDate, endLocalTime);

        place.setStartTime(startDateTime);
        place.setEndTime(endDateTime);


        service.register(place);

        return "redirect:/place/list";
    }

    /**
     * 장소 삭제
     * @param id
     * @return
     */
    @PostMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            service.remove(id);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    @PostMapping("/modify/{id}")
    public ResponseEntity<?> modify(@PathVariable Long id, Place place) {
        try {
            System.out.println(place);
            service.modify(place);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
}
