package com.springboot.mycgv.controller;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jwt")
public class JwtController {

    private final MemberService memberService;

    @GetMapping("jwt-join")
    public String registerGET(MemberDto memberDto) {
        return "jwt/jwt-join";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute MemberDto memberDto) {

        memberService.register(memberDto);

        return "redirect:/login";
    }
}
