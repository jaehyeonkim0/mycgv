//package com.springboot.mycgv.controller;
//
//import com.springboot.mycgv.dto.MemberDto;
//import com.springboot.mycgv.service.MemberService2;
//import com.springboot.mycgv.service.ValidService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.validation.Valid;
//import java.util.Map;
//
//@AllArgsConstructor
//@Controller
//@Slf4j
//public class MemberController2 {
//
//    private MemberService2 memberService2;
//    private ValidService validService;
//    @GetMapping("/login")
//    public String login() {
//        return "login/login";
//    }
//
//    @GetMapping("/signup")
//    public String signUp(MemberDto memberDto) {
//        return "signUp/signUp";
//    }
//
//    @PostMapping("/signup")
//    public String insert(@Valid @ModelAttribute MemberDto memberDto, Errors errors, Model model) {
//
//        if (errors.hasErrors()) {
//            /* 회원가입 실패시 입력 데이터 유지 : @ModelAttribute MemberDto memberDto */
//            Map<String, String> validateResult = validService.validateHandler(errors);
//            // map.keySet() -> 모든 key값을 갖고온다.
//            // 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
//            for (String key : validateResult.keySet()) {
//                // ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
//                model.addAttribute(key, validateResult.get(key));
//            }
//            return "signUp/signUp";
//        }
//
//        if(memberService2.insert(memberDto) == 1) {
//            return "redirect:/login";
//        }
//        return "redirect:/signup";
//    }
//}
