package com.springboot.mycgv.controller;

import com.springboot.mycgv.config.LoginedUser;
import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.dto.CommentDto;
import com.springboot.mycgv.dto.PageDto2;
import com.springboot.mycgv.dto.UserSessionDto;
import com.springboot.mycgv.model.Board;
import com.springboot.mycgv.model.Comment;
import com.springboot.mycgv.service.BoardService;
import com.springboot.mycgv.service.CommentService;
import com.springboot.mycgv.service.PageService;
import com.springboot.mycgv.service.ValidService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class BoardController {
    //Controller에서는 Dto를 받고 Service에서 Dto -> Entity 변환
    //Repository에서 가져올 땐 대부분이 Entity 타입인데
    //Service에서는 Entity -> Dto 변환하여 Controller로 넘겨줌

    private BoardService boardService;
    private PageService pageService;
//    private FileUploadService fileUploadService;
    private ValidService validService;
    private CommentService commentService;

//    @GetMapping("board_write")
//    public String board_wirte(Authentication authentication, Model model) {
//        model.addAttribute("member_id", authentication.getName());
//        return "board/board_write";
//    }

//    @GetMapping("board_write")
//    public String board_wirte(@LoginedUser UserSessionDto userSessionDto, Model model) {
//        model.addAttribute("member_id", userSessionDto.getId());
//        return "board/board_write";
//    }

    @GetMapping("board_write")
    public String board_wirte() {
        return "board/board_write";
    }

//    @GetMapping("/board_list")
//    public String board_list(Model model) {
//        List<BoardDto> list = boardService.list();
//        model.addAttribute("list", list);
//        return "board/board_list2";
//    }

//    @PostMapping("board_write")
//    public String board_write_proc(@Valid @ModelAttribute BoardDto boardDto,
//                                   Errors errors, Model model) throws IOException {
////        if (errors.hasErrors()) {
////            /* 회원가입 실패시 입력 데이터 유지 : @ModelAttribute MemberDto memberDto */
////            Map<String, String> validateResult = validService.validateHandler(errors);
////            // map.keySet() -> 모든 key값을 갖고온다.
////            // 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
////            for (String key : validateResult.keySet()) {
////                // ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
////                model.addAttribute(key, validateResult.get(key));
////            }
////            return "board/board_write";
////        }else {
////            boardService.insert(boardDto);
////        }
//
//        boardService.insert(boardDto, boardDto.getId());
//        return "redirect:/board_list";
//    }


//    @PostMapping("board_write")
//    public String board_write_proc(@Valid @ModelAttribute BoardDto boardDto,
//                                   @LoginedUser UserSessionDto userSessionDto,
//                                   Errors errors, Model model) throws IOException {
////        if (errors.hasErrors()) {
////            /* 회원가입 실패시 입력 데이터 유지 : @ModelAttribute MemberDto memberDto */
////            Map<String, String> validateResult = validService.validateHandler(errors);
////            // map.keySet() -> 모든 key값을 갖고온다.
////            // 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
////            for (String key : validateResult.keySet()) {
////                // ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
////                model.addAttribute(key, validateResult.get(key));
////            }
////            return "board/board_write";
////        }else {
////            boardService.insert(boardDto);
////        }
//        boardDto.setId(userSessionDto.getId());
//        boardService.insert(boardDto);
//        return "redirect:/board_list";
//    }


    @PostMapping("board_write")
    public String board_write_proc1(@LoginedUser UserSessionDto userSessionDto,
                                    @Valid @ModelAttribute BoardDto boardDto,
                                    Errors errors, Model model) throws IOException {
        boardDto.setId(userSessionDto.getId());
        boardService.multipleInsert(boardDto);

        return "redirect:/board_list";
//        if (errors.hasErrors()) {
//            /* 회원가입 실패시 입력 데이터 유지 : @ModelAttribute MemberDto memberDto */
//            Map<String, String> validateResult = validService.validateHandler(errors);
//            // map.keySet() -> 모든 key값을 갖고온다.
//            // 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
//            for (String key : validateResult.keySet()) {
//                // ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
//                model.addAttribute(key, validateResult.get(key));
//            }
//            return "board/board_write";
//        }else {
//            boardService.insert(boardDto);
//        }

    }

//    @GetMapping("board_content/{bid}/{page}")
//    public String board_content(Model model,
//                                @PathVariable Long bid,
//                                @PathVariable(required = false) String page,
//                                @RequestParam(required = false, defaultValue = "") String searchText) {
////        log.info("접속한 유저 정보 = {}", userSessionDto.getId());
//        boardService.updateHits(bid);
//        model.addAttribute("board", boardService.content(bid));
//        model.addAttribute("page", page);
//        model.addAttribute("searchText", searchText);
//
//        return "board/board_content";
//    }

//    @GetMapping("board_content2/{bid}/{page}")
//    public String board_content(Model model,
//                                @PathVariable Long bid,
//                                @PathVariable(required = false) String page,
//                                @RequestParam(required = false, defaultValue = "") String searchText,
//                                @LoginedUser UserSessionDto userSessionDto) {
////        log.info("접속한 유저 정보 = {}", userSessionDto.getId());
//        boardService.updateHits(bid);
//        List<CommentDto> commentDtoList = commentService.findAll(bid);
//
//        model.addAttribute("user", userSessionDto);
//        model.addAttribute("commentList", commentDtoList);
//        model.addAttribute("board", boardService.multipleContent(bid));
//        model.addAttribute("page", page);
//        model.addAttribute("searchText", searchText);
//
//        return "board/board_content2";
//    }

    @GetMapping("board_content2/{bid}/{page}")
    public String board_content(Model model,
                                @PathVariable Long bid,
                                @PathVariable(required = false) String page,
                                @RequestParam(required = false, defaultValue = "") String searchText,
                                @PageableDefault(size = 5, sort = "cid",
                                        direction = Sort.Direction.DESC) Pageable pageable) {
//        log.info("접속한 유저 정보 = {}", userSessionDto.getId());
        boardService.updateHits(bid);
        Page<Comment> commentList = commentService.findAll(bid, pageable);

        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList) {
            commentDtoList.add(CommentDto.toCommentDTO(comment, bid));
        }

        model.addAttribute("commentList", commentList);
        model.addAttribute("commentDtoList", commentDtoList);
        model.addAttribute("board", boardService.multipleContent(bid));
        model.addAttribute("page", page);
        model.addAttribute("searchText", searchText);

        return "board/board_content2";
    }

//    @GetMapping("board_update/{bid}")
//    public String board_update(Model model, @PathVariable Long bid) {
//        model.addAttribute("board", boardService.content(bid));
//        return "board/board_update";
//
//    }

    @GetMapping("board_update/{bid}")
    public String board_update(Model model, @PathVariable Long bid) {
        model.addAttribute("board", boardService.multipleContent(bid));
        return "board/board_update";
    }

//    @PostMapping("board_update")
//    public String board_update_proc(BoardDto boardDto, Model model) throws IOException {
//        BoardDto updateBoardDto = boardService.update(boardDto);
//        model.addAttribute("board", updateBoardDto);
//
//        return "board/board_content";
//    }

//    @PostMapping("board_update")
//    public String board_update_proc(BoardDto boardDto, Model model) throws IOException {
//        boardService.update(boardDto);
//
//        return "redirect:/board_list";
//    }

    @PostMapping("board_update")
    public String board_update_proc(BoardDto boardDto, Model model) throws IOException {
        boardService.multipleUpdate(boardDto);

        return "redirect:/board_list";
    }

//    @GetMapping("/board_list")
//    public String board_list(Model model, @PageableDefault(size = 5) Pageable pageable) {
//        Page<Board> list = boardService.list(pageable);
//
//        int startPage = Math.max(1, list.getPageable().getPageNumber() - 4);
//        int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 4);
//
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("list", list);
//        return "board/board_list2";
//    }


//    @GetMapping("/board_list")
//    public String board_list(Model model,
//                             @PageableDefault(size = 5, sort = "bid",
//                             direction = Sort.Direction.DESC) Pageable pageable) {
//        Page<Board> list = boardService.list(pageable);
//
//        List<BoardDto> boardDtoList = new ArrayList<>();
//        for(Board board : list) {
//            boardDtoList.add(BoardDto.toBoardDto(board));
//        }
//
//        int startPage = Math.max(1, list.getPageable().getPageNumber() - 5);
//        int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 5);
//
//        model.addAttribute("startPage", 1);
//        model.addAttribute("endPage", endPage);
//        model.addAttribute("list", list);
//        model.addAttribute("board", boardDtoList);
//
//        return "board/board_list2";
//    }

    @GetMapping("/board_list")
    public String board_list(Model model,
                             @PageableDefault(size = 5, sort = "bid", direction = Sort.Direction.DESC) Pageable pageable,
                             @RequestParam(required = false, defaultValue = "") String searchText) {

        Page<Board> list = boardService.list(pageable, searchText);

        PageDto2 pageDto2 = PageDto2.toPageDto(list);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : list) {
            boardDtoList.add(BoardDto.toMultipleFileBoardDto(board));
        }

        model.addAttribute("page", pageDto2);
        model.addAttribute("board", boardDtoList);

        return "board/board_list3";
    }

    @GetMapping("/board_delete/{bid}")
    public String delete(@PathVariable Long bid) {
        boardService.delete(bid);
        return "redirect:/board_list";
    }

    /***----------------------------------------------------------------------------------------------------*/

//    @Autowired
//    public BoardController(BoardService boardService, PageService pageService,
//                           FileUploadService fileUploadService, ValidService validService) {
//        this.boardService = boardService;
//        this.pageService = pageService;
//        this.fileUploadService = fileUploadService;
//        this.validService = validService;
//    }

//    @GetMapping("board_write")
//    public String board_wirte() {
//        return "board/board_write";
//    }

//    @GetMapping("board_list/{page}")
//    public String board_list(Model model, @PathVariable String page) {
//        PageDto pageDto = pageService.getPageResult(new PageDto(page, "board"));
//        model.addAttribute("list", boardService.list(pageDto));
//        model.addAttribute("page", pageDto);
//        return "board/board_list";
//    }


//    @GetMapping("board_content/{bid}/{page}")
//    public String board_content(Model model, @PathVariable String page, @PathVariable String bid) {
//        model.addAttribute("page", page);
//        model.addAttribute("board", boardService.content(bid));
//        return "board/board_content";
//    }

//    @GetMapping("board_update/{bid}/{page}")
//    public String board_update(Model model, @PathVariable String page, @PathVariable String bid) {
//        model.addAttribute("board", boardService.content(bid));
//        model.addAttribute("page", page);
//        return "board/board_update";
//    }

//    @PostMapping("board_update")
//    public String board_update_proc(BoardDto boardDto) throws Exception {//pathvariable("page") 안받는 이유 : BoardDto에 page 필드가 있기 때문에
//        //새로운 파일을 선택하면 기존의 파일(oldFileName; bsfile)을 삭제해야한다; 선택 안하면 삭제 안해도 됨 <- 제일 먼저 작성!
//        String oldBsfile = boardDto.getBsfile(); //if client upload new file, old file name is in oldBsFile;
//        boardDto = (BoardDto) fileUploadService.fileCheck(boardDto); //checking whether there's file or not; if client upload new file, insert new bfile, bsfile name
//
//        int result = boardService.update(boardDto);
//        System.out.println("수정 결과= "+result);
//
//        if(result == 1 ) {
//            if(!boardDto.getFile1().isEmpty()) {
//                //if is not null, save new file
//                fileUploadService.fileSave(boardDto);
//                //delete original file
//                fileUploadService.fileDelete(oldBsfile);
//            }
//        } else {
//            System.out.println("수정 실패");
//        }
//
//        return "redirect:/board_list/"+boardDto.getPage()+"/";
//
//    }

//    @PostMapping("board_write")
//    public String board_write_proc(BoardDto boardDto) throws Exception {
//        //파일업로드 서비스 추가 & insert
//        //checking whether there's file or not ; if it is, boardDto.setBfile();, setBsfile();
//        boardDto = (BoardDto)fileUploadService.fileCheck(boardDto);
//        int result = boardService.insert(boardDto);
//        if(result == 1) {
//            fileUploadService.fileSave(boardDto); // add try~catch or throws Exception so that fix error
//        }
//        return "redirect:/board_list";
//    }

    @GetMapping("board_delete/{bid}/{page}")
    public String board_delete(@PathVariable String page, @PathVariable String bid, Model model) {
        model.addAttribute("bid", bid);
        model.addAttribute("page", page);
        return "board/board_delete";
    }

//    @PostMapping("board_delete")
//    public String board_delete_proc(BoardDto boardDto) throws Exception {
//        //checking if is there any file;
//        String oldBsFile = boardService.getBsfile(boardDto.getBid());
//        int result = boardService.delete(boardDto.getBid());
//
//        if(result == 1) {
//            if(oldBsFile != null) fileUploadService.fileDelete(oldBsFile);
//        }
//        return "redirect:/board_list/"+boardDto.getPage()+"/";
//    }

    @GetMapping("board_list_json")
    public String board_list_json(){
        return "/board/board_list_json";
    }


}
