package com.hyundairoad.hyundairoad.place.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log
@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private PlaceService service;

    @GetMapping("/home")
    public String home() {
        return "index";
    }
}
