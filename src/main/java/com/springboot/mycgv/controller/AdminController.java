package com.springboot.mycgv.controller;

import com.springboot.mycgv.dto.NoticeDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.service.NoticeService;
import com.springboot.mycgv.service.PageService;
import com.springboot.mycgv.service.ValidService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {

    private PageService pageService;
    private NoticeService noticeService;
    private ValidService validService;

//    private FileUploadService fileUploadService;

//    @Autowired
//    public AdminController(PageService pageService, NoticeService noticeService,
//                           ValidService validService, FileUploadService fileUploadService) {
//        this.pageService = pageService;
//        this.noticeService = noticeService;
//        this.validService = validService;
//        this.fileUploadService = fileUploadService;
//    }

    @GetMapping("index")
    public String admin_index() {
        return "admin/admin_index";
    }

    @GetMapping("notice_list/{page}")
    public String admin_notice_list(@PathVariable String page, Model model) {

        PageDto pageDto = pageService.getPageResult(new PageDto(page, "notice"));
        model.addAttribute("list", noticeService.list(pageDto));
        model.addAttribute("page", pageDto);
        return "admin/notice/admin_notice_list";
    }

    @GetMapping("notice_content/{nid}/{page}")
    public String admin_notice_content(Model model,
                                       @PathVariable String page, @PathVariable String nid ) {

        model.addAttribute("notice", noticeService.content(nid));
        model.addAttribute("page", page);
        return "admin/notice/admin_notice_content";
    }

    @GetMapping("notice_write")
    public String admin_notice_write(NoticeDto noticeDto) {
        return "admin/notice/admin_notice_write";
    }

    @PostMapping("notice_write")
    public String admin_notice_write_proc(@Valid @ModelAttribute NoticeDto noticeDto, Errors errors, Model model) throws Exception {

        if (errors.hasErrors()) {
            Map<String, String> validateResult = validService.validateHandler(errors);
            // map.keySet() -> 모든 key값을 갖고온다.
            // 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
            for (String key : validateResult.keySet()) {
                // ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
                model.addAttribute(key, validateResult.get(key));
            }
            return "admin/notice/admin_notice_write";
        } else if(noticeService.insert(noticeDto) == 1) {
            return "redirect:/notice_list/1/";
        }

        return "redirect:/notice_write";
    }





}
