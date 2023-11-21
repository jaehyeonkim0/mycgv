package com.springboot.mycgv.dto;

import com.springboot.mycgv.model.Comment;
import com.springboot.mycgv.repository.BoardRepository;
import com.springboot.mycgv.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CommentTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void commentInsert() {

        for(Long i = 1L; i<20L; i++) {
            commentRepository.save(
                    Comment.builder()
                            .cid(i)
                            .commentWriter("댓글 작성자 " + i)
                            .commentContents("댓글 콘텐츠 " + i)
                            .board(boardRepository.findOneByBid(21L))
                            .build());
        }
    }

}