package com.springboot.mycgv.controller;

import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.service.MemberService;
import com.springboot.mycgv.service.ValidService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@AllArgsConstructor
@Controller
@Slf4j
public class MemberController {

    private MemberService memberService;
    private ValidService validService;

    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    @GetMapping("join")
    public String join(MemberDto memberDto) {
        return "join/join";
    }

//    @PostMapping("join")
//    public String join_proc(@Valid @ModelAttribute MemberDto memberDto, Errors errors, Model model) {
//
//        /* post요청시 넘어온 user 입력값에서 Validation에 걸리는 경우 */
//        if (errors.hasErrors()) {
//            /* 회원가입 실패시 입력 데이터 유지 : @ModelAttribute MemberDto memberDto */
//            Map<String, String> validateResult = validService.validateHandler(errors);
//            // map.keySet() -> 모든 key값을 갖고온다.
//            // 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
//            for (String key : validateResult.keySet()) {
//                // ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
//                model.addAttribute(key, validateResult.get(key));
//            }
//            return "join/join";
//        } else if(memberService.idCheck(memberDto.getId()) == 1) {
//            model.addAttribute("valid_id", "중복된 아이디입니다");
//            return "join/join";
//        } else if(memberService.insert(memberDto) == 1) {
//            return "redirect:/login";
//        }
//
//        return "redirect:/login";
//    }

    @PostMapping("join")
    public String join_proc(@Valid @ModelAttribute MemberDto memberDto, Errors errors, Model model) {

        /* post요청시 넘어온 user 입력값에서 Validation에 걸리는 경우 */
        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 유지 : @ModelAttribute MemberDto memberDto */
            Map<String, String> validateResult = validService.validateHandler(errors);
            // map.keySet() -> 모든 key값을 갖고온다.
            // 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
            for (String key : validateResult.keySet()) {
                // ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
                model.addAttribute(key, validateResult.get(key));
            }
            return "join/join";
        }else {
            memberService.save(memberDto);
        }
        return "redirect:/login";
    }

    @GetMapping("mypage")
    public String mypage() {
        return "/mypage/mypage";
    }

//    @GetMapping("logout")
//    public String logout(HttpSession session, Model model) {
//        SessionDto svo = (SessionDto)session.getAttribute("svo");
//
//        if(svo != null) {
//            session.invalidate();
//            model.addAttribute("logout_result", "ok");
//        }
//        return "index";
//    }



}
