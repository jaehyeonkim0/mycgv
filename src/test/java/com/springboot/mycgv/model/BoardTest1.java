package com.springboot.mycgv.model;

import com.springboot.mycgv.model.board.Board;
import com.springboot.mycgv.repository.BoardRepository;
import com.springboot.mycgv.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BoardTest1 {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void insert() {

        for(Long i = 60L; i<99L; i++) {
            boardRepository.save(
                    Board.builder()
                            .bid(i)
                            .btitle("게시판 제목 테스트 " + i)
                            .bcontent("게시판 내용 테스트 " + i)
                            .member(memberRepository.findOneById("test"))
                            .fileAttached(0)
                            .build());
        }
    }

}