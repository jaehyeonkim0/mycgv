package com.springboot.mycgv.controller;

import com.springboot.mycgv.Validator.JoinValidator;
import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.service.MemberService;
import com.springboot.mycgv.service.ValidService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final ValidService validService;
    private final JoinValidator joinValidator;

    //@InitBinder 어노테이션을 사용하면 컨트롤러에서 요청을 처리하기 전에 데이터 바인딩을 커스터마이징할 수 있다.
    //WebDataBinder는 스프링에서 제공하는 데이터 바인딩을 처리하는 클래스다.

    //@InitBinder 어노테이션을 사용하면 컨트롤러의 메소드에서 WebDataBinder를 파라미터로 받아와서,
    //이를 통해 데이터 바인딩 처리를 커스터마이징할 수 있다.
    //주로 유효성 검사나 데이터 포맷 변환과 관련된 작업들을 수행하기 위해 사용한다.

    //이러한 검사 클래스(joinValidator)를 WebDataBinder에 추가함으로써,
    //해당 필드에 대한 유효성 검사를 수행할 수 있게 됩니다.
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(joinValidator);
    }

    @GetMapping("login")
    public String login() {
        return "login/login";
    }

    @GetMapping("join")
    public String join(MemberDto memberDto) {
        return "join/join";
    }

    @PostMapping("join")
    public String join_proc(@Valid @ModelAttribute MemberDto memberDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
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

}
