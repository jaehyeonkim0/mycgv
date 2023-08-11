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

import java.util.List;

@Controller
public class NoticeController {

    private NoticeService noticeService;
    private PageService pageService;

    @Autowired
    public NoticeController(NoticeService noticeService, PageService pageService) {
        this.noticeService = noticeService;
        this.pageService = pageService;
    }

    @GetMapping("notice_content/{nid}/{page}")
    public String notice_content(@PathVariable String nid,
                                 @PathVariable String page,
                                 Model model) {
        model.addAttribute("notice", noticeService.content(nid));
        model.addAttribute("page", page);
        return "notice/notice_content";
    }

    @GetMapping("notice_list/{page}")
    public String notice_list(@PathVariable String page, Model model){
//        List<NoticeDto> noticeDto = noticeService.list();
//        PageDto pageDto = new PageDto(); // page, serviceName 넣으려면 코드 3줄이 필요 그래서 생성자로 받음 line 33
//        pageDto.setPage(page);
//        pageDto.setServiceName("notice");

        PageDto pageDto = pageService.getPageResult(new PageDto(page, "notice"));
        model.addAttribute("list", noticeService.list(pageDto));
        model.addAttribute("page", pageDto);
        return "/notice/notice_list";
    }

}
