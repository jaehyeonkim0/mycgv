package com.springboot.mycgv.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BoardTest {

    @Test
    @DisplayName("게시판 리스트 테스트")
    void list() {
        for(long i=51; i<100; i++) {
            Board board = Board.builder()
                    .bid(i)
                    .btitle("hello")
                    .bcontent("world")
                    .member(Member.builder().id("test").build())
                    .build();
        }

    }

    @Test
    @DisplayName("JPA ManyToOne Test")
    public void ManyToOne() {
        Member member = Member.builder()
                .id("test1")
                .build();

        Board board = Board.builder()
                .bid(9L)
                .member(member)
                .build();

        member.getLists().add(board);

    }
}