package com.hyundairoad.hyundairoad;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class TestController {

    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!!!!!";
    }
}