package com.springboot.mycgv.controller;

import com.springboot.mycgv.dto.CommentDto;
import com.springboot.mycgv.model.Comment;
import com.springboot.mycgv.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;
//    @PostMapping("/save")
//    public ResponseEntity save(@ModelAttribute CommentDto commentDto) {
//        System.out.println("commentDto = " + commentDto);
//        Long saveResult = commentService.save(commentDto);
//        if (saveResult != null) {
//            List<CommentDto> commentDtoList = commentService.findAll(commentDto.getBid());
//            return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @ModelAttribute CommentDto commentDto,
                               Errors errors,
                               @PageableDefault(size = 5, sort = "cid",
                                       direction = Sort.Direction.DESC) Pageable pageable) {

        Long saveResult = commentService.save(commentDto);

        if (saveResult != null) {
            Page<Comment> commentList = commentService.findAll(commentDto.getBid(), pageable);

            List<CommentDto> commentDtoList = new ArrayList<>();
            for(Comment comment : commentList) {
                CommentDto commentDto1 = new CommentDto();
                commentDto1.setCid(comment.getCid());
                commentDto1.setCommentWriter(comment.getCommentWriter());
                commentDto1.setCommentContents(comment.getCommentContents());
                commentDto1.setBid(comment.getBoard().getBid());
                commentDto1.setCommentCreatedTime(comment.getCreatedTime());

                commentDtoList.add(commentDto1);
            }
            return new ResponseEntity<>(commentDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }





}
