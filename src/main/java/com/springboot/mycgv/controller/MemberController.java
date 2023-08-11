package com.springboot.mycgv.controller;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.dto.SessionDto;
import com.springboot.mycgv.service.MemberService;
import lombok.AllArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@AllArgsConstructor
@Controller
public class MemberController {

    private MemberService memberService;

    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    @GetMapping("join")
    public String join() {
        return "join/join";
    }

    @PostMapping("login")
    public String loginProc(MemberDto memberDto, Model model,
                            HttpSession session) {
         SessionDto sessionDto = memberService.login(memberDto);
         if(sessionDto.getLoginresult() ==1) {
             session.setAttribute("svo", sessionDto);
             model.addAttribute("login_result","ok");
         }
         return "index";
    }

    @GetMapping("logout")
    public String logout(HttpSession session, Model model) {
        SessionDto svo = (SessionDto)session.getAttribute("svo");

        if(svo != null) {
            session.invalidate();
            model.addAttribute("logout_result", "ok");
        }
        return "index";
    }

    @PostMapping("join")
    public String join_proc(MemberDto memberDto, Model model) {
        if(memberService.join(memberDto) == 1) {
            model.addAttribute("join_result", "ok");
        }
        return "/login/login";
    }

    @GetMapping("mypage")
    public String mypage() {
        return "/mypage/mypage";
    }



}
