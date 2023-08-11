package com.springboot.mycgv.controller;

import com.springboot.mycgv.dto.NoticeDto;
import com.springboot.mycgv.dto.PageDto;
import com.springboot.mycgv.service.NoticeService;
import com.springboot.mycgv.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    private PageService pageService;
    private NoticeService noticeService;

    @Autowired
    public AdminController(PageService pageService, NoticeService noticeService) {
        this.pageService = pageService;
        this.noticeService = noticeService;
    }


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


}
