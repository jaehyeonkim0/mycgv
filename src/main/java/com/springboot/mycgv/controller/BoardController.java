package com.springboot.mycgv.controller;

import com.springboot.mycgv.config.LoginedUser;
import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.dto.CommentDto;
import com.springboot.mycgv.dto.PageDto2;
import com.springboot.mycgv.dto.UserSessionDto;
import com.springboot.mycgv.model.Board;
import com.springboot.mycgv.model.Comment;
import com.springboot.mycgv.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final PageService pageService;
    private final CommentService commentService;
    private final ValidService validService;
    private final FileUploadService fileUploadService;

    /**
     * 게시물 작성 페이지
     * @return 게시물 작성 VIEW
     */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("board_write")
    public String board_wirte() {
        return "board/board_write";
    }

    /**
     * 게시물 작성
     * @param userSessionDto 로그인 사용자 정보
     * @param boardDto 게시물 제목, 내용, 파일
     * @param errors 유효성 체크 시, 발생 가능한 에러
     * @param model
     * @return 게시판 리스트 VIEW
     * @throws IOException
     */
    @PostMapping("board_write")
    public String board_write_proc(@Valid @ModelAttribute BoardDto boardDto,
                                   Errors errors, Model model) throws IOException {

        if (errors.hasErrors()) {
            Map<String, String> validateResult = validService.validateHandler(errors);
            for (String key : validateResult.keySet()) {
                model.addAttribute(key, validateResult.get(key));
            }
            return "board/board_write";
        }else {
            boardService.saveBoard(boardDto);
        }
        return "redirect:/board_list";
    }

    /**
     * 게시판 리스트
     * @param model
     * @param pageable 페이징 정보
     * @param searchText 검색 키워드
     * @return 게시판 리스트 VIEW
     */
    @GetMapping("/board_list")
    public String board_list(Model model,
                             @PageableDefault(size = 5, sort = "bid", direction = Sort.Direction.DESC) Pageable pageable,
                             @RequestParam(required = false, defaultValue = "") String searchText) {

        Page<Board> list = boardService.listBoard(pageable, searchText);

        PageDto2 pageDto2 = PageDto2.toPageDto(list);
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : list) {
            boardDtoList.add(BoardDto.toDetailBoardDto(board));
        }

        int startBlockPage = (pageDto2.getReqPage()/5) * 5 + 1; // 1
        int endBlockPage = startBlockPage + 5 - 1; //5
        endBlockPage = pageDto2.getTotalPage() < endBlockPage ? pageDto2.getTotalPage() : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("page", pageDto2);
        model.addAttribute("board", boardDtoList);

        return "board/board_list";
    }

    /**
     * 게시물 상세보기
     * @param model
     * @param bid 게시물 ID
     * @param page 게시판 리스트 페이징 현재 페이지 정보
     * @param searchText 게시판 검색 키워드
     * @param pageable 페이징 인터페이스
     * @return 게시판 상세 페이지 VIEW
     */
//    @PreAuthorize("isAuthenticated()")
    @GetMapping("board_content/{bid}/{page}")
    public String board_content(Model model,
                                @PathVariable Long bid,
                                @PathVariable(required = false) String page,
                                @RequestParam(required = false, defaultValue = "") String searchText,
                                @PageableDefault(size = 5, sort = "cid",
                                        direction = Sort.Direction.DESC) Pageable pageable) {
        boardService.updateHits(bid);
        Page<Comment> commentList = commentService.findAll(bid, pageable);

        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList) {
            commentDtoList.add(CommentDto.toCommentDTO(comment, bid));
        }

        model.addAttribute("commentDtoList", commentDtoList);
        model.addAttribute("board", boardService.detailBoard(bid));
        model.addAttribute("page", page);
        model.addAttribute("searchText", searchText);

        return "board/board_content";
    }

    /**
     * 게시물 수정 페이지
     * @param model
     * @param bid 게시물 ID
     * @param page 게시판 리스트 페이징 현재 페이지 정보
     * @return 게시물 수정 VIEW
     */
    @GetMapping("board_update/{bid}/{page}")
    public String board_update(Model model, @PathVariable Long bid,
                               @PathVariable(required = false) String page) {
        model.addAttribute("board", boardService.detailBoard(bid));
        return "board/board_update";
    }

    /**
     * 게시물 수정
     * @param boardDto 게시물 정보
     * @param model
     * @return 게시판 리스트 VIEW
     * @throws IOException
     */
//    @PreAuthorize("principal.username == #boardDto.member.id")
    @PostMapping("board_update")
    public String board_update_proc(BoardDto boardDto, Model model) throws IOException {
        boardService.modifyBoard(boardDto);
        return "redirect:/board_list";
    }

    /**
     * 게시판 삭제
     * @param bid 게시판 ID
     * @return 게시판 리스트 VIEW
     */
    @GetMapping("/board_delete/{bid}")
    public String delete(@PathVariable Long bid) throws IOException{
        boardService.deleteBoard(bid);
        return "redirect:/board_list";
    }






















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

//    @GetMapping("board_delete/{bid}/{page}")
//    public String board_delete(@PathVariable String page, @PathVariable String bid, Model model) {
//        model.addAttribute("bid", bid);
//        model.addAttribute("page", page);
//        return "board/board_delete";
//    }

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

//    @GetMapping("board_list_json")
//    public String board_list_json(){
//        return "/board/board_list_json";
//    }


}
