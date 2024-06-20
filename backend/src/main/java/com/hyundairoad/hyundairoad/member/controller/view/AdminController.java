package com.hyundairoad.hyundairoad.member.controller.view;

import com.hyundairoad.hyundairoad.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;

    @GetMapping("/home")
    public String getAdminPage() {
        return "home";
    }
}
