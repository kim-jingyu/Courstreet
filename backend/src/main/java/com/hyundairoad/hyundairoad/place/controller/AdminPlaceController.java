package com.hyundairoad.hyundairoad.place.controller;

import com.hyundairoad.hyundairoad.place.domain.Place;
import com.hyundairoad.hyundairoad.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/place")
@RequiredArgsConstructor
public class AdminPlaceController {

    private final PlaceService service;

    /**
     * 관리자 장소 조회
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", service.getAllList());
        return "admin/place/list";
    }

    /**
     * 관리자 장소 등록
     * @param place
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Place place) {
        service.register(place);
        return ResponseEntity.ok().body("Success");
    }

    /**
     * 관리자 장소 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            service.remove(id);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    /**
     * 관리자 장소 수정
     * @param place
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Place place) {
        try {
            service.update(place);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }
}
