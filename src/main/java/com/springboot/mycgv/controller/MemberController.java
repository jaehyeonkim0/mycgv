package com.springboot.mycgv.controller;

import com.springboot.mycgv.Validator.JoinValidator;
import com.springboot.mycgv.dto.MemberDto;
import com.springboot.mycgv.service.MemberService;
import com.springboot.mycgv.service.ValidService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ValidService validService;
    private final JoinValidator joinValidator;
    //    private final TokenProvider tokenProvider;

    /**
     * @@InitBinder 어노테이션을 사용하면 컨트롤러에서 요청을 처리하기 전에 데이터 바인딩을 커스마이징할 수 있다.
     * WebDataBinder는 스프링에서 제공하는 데이터 바인딩을 처리하는 클래스다.
     * @@InitBinder 어노테이션을 사용하면 컨트롤러의 메소드에서 WebDataBinder를 파라미터로 받아와서,
     * 이를 통해 데이터 바인딩 처리를 커스터마이징할 수 있다.
     * 주로 유효성 검사나 데이터 포맷 변환과 관련된 작업들을 수행하기 위해 사용한다.
     * <p>
     * 이러한 검사 클래스(joinValidator)를 WebDataBinder에 추가함으로써,
     * 해당 필드에 대한 유효성 검사를 수행할 수 있게 됩니다.
     */

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(joinValidator);
    }

    @GetMapping("login")
    public String login() {
        return "login/login";
    }

//    @PostMapping("login")
//    public String login_proc(MemberDto memberDto, HttpServletRequest request,
//                             HttpServletResponse response) {
//
//        String token = memberService.login(memberDto.getId(), memberDto.getPassword());
//
//        log.info("MemberController getHeader() = {}", request.getHeader("Authorization"));
//        log.info("token = {}", token);
//
//        Cookie cookie = new Cookie("jwtToken", token);
//        cookie.setMaxAge(1 * 60);
//        response.addCookie(cookie);
//
//        return "redirect:/";
//    }


    @GetMapping("join")
    public String join(MemberDto memberDto,
                       HttpServletRequest request,
                       Model model) {

        HttpSession session = request.getSession(false);
        String name = (String) session.getAttribute("name");
        String oauthId = (String) session.getAttribute("oauthId");
        Boolean social = (Boolean) session.getAttribute("social");
        String pnumber = (String) session.getAttribute("pnumber");


        model.addAttribute("name", name);
        model.addAttribute("oauthId", oauthId);
        model.addAttribute("social", social);
        model.addAttribute("pnumber", pnumber);

        return "join/join";
    }

    @PostMapping("join")
    public String join_proc(@Valid @ModelAttribute MemberDto memberDto,
                            Errors errors, HttpSession session,
                            Model model) {
        if (errors.hasErrors()) {
            Map<String, String> validateResult = validService.validateHandler(errors);
            // map.keySet() -> 모든 key값을 갖고온다.
            // 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
            for (String key : validateResult.keySet()) {
                // ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
                model.addAttribute(key, validateResult.get(key));
            }
            return "join/join";
        } else {
            memberService.save(memberDto);
            session.invalidate(); //session 제거함으로 OAuth2 로그인,회원가입으로 생성된 session 속성값 제거
        }
        return "redirect:/login";
    }

    @GetMapping("mypage")
    public String mypage() {
        return "/mypage/mypage";
    }

    @GetMapping("denied")
    public String denied(){
        return "denied";
    }

//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        new SecurityContextLogoutHandler().logout(request, response,
//                SecurityContextHolder.getContext().getAuthentication());
//        return "redirect:/login";
//    }

}
