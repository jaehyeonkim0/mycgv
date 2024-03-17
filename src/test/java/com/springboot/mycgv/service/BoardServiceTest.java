package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.model.board.Board;
import com.springboot.mycgv.repository.BoardRepository;
import com.springboot.mycgv.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
class BoardServiceTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("ModelMapper Test")
    public void convert() {

        Optional<Board> result = boardRepository.findById(42L);


        BoardDto boardDto = modelMapper.map(result, BoardDto.class);

        log.info("게시판 = {}", boardDto.getClass());
        log.info("게시판 = {}", result.getClass());
    }

    @Test
    void findAll() {
        boardRepository.findAll().stream().forEach(System.out::println);
    }


    @Test
    void insert() {
        for(int i=2; i<10; i++) {
            Board board = Board.builder()
                    .btitle("게시판 입력 테스트" + i)
                    .bcontent("게시판 입력 테스트 내용 " + i)
                    .member(memberRepository.findOneById("test3"))
                    .build();

            boardRepository.save(board);
        }
    }
}