package com.springboot.mycgv.controller;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.service.BoardService;
import com.springboot.mycgv.service.FileUploadService;
import com.springboot.mycgv.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    private BoardService boardService;
    private PageService pageService;

    private FileUploadService fileUploadService;

    @Autowired
    public BoardController(BoardService boardService, PageService pageService, FileUploadService fileUploadService) {
        this.boardService = boardService;
        this.pageService = pageService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("board_list/{page}")
    public String board_list(Model model, @PathVariable String page) {
        PageDto pageDto = pageService.getPageResult(new PageDto(page, "board"));
        model.addAttribute("list", boardService.list(pageDto));
        model.addAttribute("page", pageDto);
        return "board/board_list";
    }

    @GetMapping("board_content/{bid}/{page}")
    public String board_content(Model model, @PathVariable String page, @PathVariable String bid) {
        model.addAttribute("page", page);
        model.addAttribute("board", boardService.content(bid));
        return "board/board_content";
    }

    @GetMapping("board_update/{bid}/{page}")
    public String board_update(Model model, @PathVariable String page, @PathVariable String bid) {
        model.addAttribute("board", boardService.content(bid));
        model.addAttribute("page", page);
        return "board/board_update";
    }

    @PostMapping("board_update")
    public String board_update_proc(BoardDto boardDto) throws Exception {//pathvariable("page") 안받는 이유 : BoardDto에 page 필드가 있기 때문에
        //새로운 파일을 선택하면 기존의 파일(oldFileName; bsfile)을 삭제해야한다; 선택 안하면 삭제 안해도 됨 <- 제일 먼저 작성!
        String oldBsfile = boardDto.getBsfile(); //if client upload new file, old file name is in oldBsFile;
        boardDto = (BoardDto) fileUploadService.fileCheck(boardDto); //checking whether there's file or not; if client upload new file, insert new bfile, bsfile name

        int result = boardService.update(boardDto);
        System.out.println("수정 결과= "+result);

        if(result == 1 ) {
            if(!boardDto.getFile1().isEmpty()) {
                //if is not null, save new file
                fileUploadService.fileSave(boardDto);
                //delete original file
                fileUploadService.fileDelete(oldBsfile);
            }
        } else {
            System.out.println("수정 실패");
        }

        return "redirect:/board_list/"+boardDto.getPage()+"/";

    }

    @GetMapping("board_write")
    public String board_wirte() {
        return "board/board_write";
    }

    @PostMapping("board_write")
    public String board_write_proc(BoardDto boardDto) throws Exception {
        //파일업로드 서비스 추가 & insert
        //checking whether there's file or not ; if it is, boardDto.setBfile();, setBsfile();
        boardDto = (BoardDto)fileUploadService.fileCheck(boardDto);
        int result = boardService.insert(boardDto);
        if(result == 1) {
            fileUploadService.fileSave(boardDto); // add try~catch or throws Exception so that fix error
        }
        return "redirect:/board_list/1/";
    }

    @GetMapping("board_delete/{bid}/{page}")
    public String board_delete(@PathVariable String page, @PathVariable String bid, Model model) {
        model.addAttribute("bid", bid);
        model.addAttribute("page", page);
        return "board/board_delete";
    }

    @PostMapping("board_delete")
    public String board_delete_proc(BoardDto boardDto) throws Exception {
        //checking if is there any file;
        String oldBsFile = boardService.getBsfile(boardDto.getBid());
        int result = boardService.delete(boardDto.getBid());

        if(result == 1) {
            if(oldBsFile != null) fileUploadService.fileDelete(oldBsFile);
        }
        return "redirect:/board_list/"+boardDto.getPage()+"/";
    }

    @GetMapping("board_list_json")
    public String board_list_json(){
        return "/board/board_list_json";
    }

}
