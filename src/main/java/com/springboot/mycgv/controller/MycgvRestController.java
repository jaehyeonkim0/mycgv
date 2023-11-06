package com.springboot.mycgv.controller;

import com.springboot.mycgv.model.SessionUser;
import com.springboot.mycgv.service.BoardService;
import com.springboot.mycgv.service.MemberService;
import com.springboot.mycgv.service.PageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@AllArgsConstructor
@Slf4j
public class MycgvRestController {


    private MemberService memberService;
    private BoardService boardService;
    private PageService pageService;

    @GetMapping("/session")
    public String sessionCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session.getAttribute(SessionUser.LOGIN_USER) == null) {
            System.out.println("세션 X");
            return "세션 X";
        }
        // 세션 id와 저장된 객체 정보 출력
        log.info(session.getId() + ", " + session.getAttribute(SessionUser.LOGIN_USER));

        return "session-check";
    }



//    @GetMapping("board_content_json_data/{bid}/")
//    public Optional<Board> board_content_json_data(@PathVariable String bid){
//        return boardService.content(bid);
//    }


//    @GetMapping("board_list_json_data/{page}/")
//    public Map board_list_json_data(@PathVariable String page){
//        Map map = new HashMap();
//        PageDto pageDto = pageService.getPageResult(new PageDto(page, "board"));
//        List<BoardDto> list = boardService.list(pageDto);
//
//        map.put("list", list);
//        map.put("page", pageDto);
//
//        return map;
//    }

    @GetMapping("idCheck/{id}")
    public String idCheck(@PathVariable String id){
        return String.valueOf(memberService.idCheck(id));
    }

}

