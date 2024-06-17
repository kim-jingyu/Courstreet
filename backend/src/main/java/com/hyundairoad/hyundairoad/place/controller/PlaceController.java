package com.hyundairoad.hyundairoad.place.controller;

import com.hyundairoad.hyundairoad.place.domain.Place;
import com.hyundairoad.hyundairoad.place.service.PlaceService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String register(Place place, RedirectAttributes rttr) {
        System.out.println(place.toString());
        service.register(place);
        System.out.println("저장!!!!!!!!!!!!!!!!!!!");
        return "redirect:/place/list";
    }
}
