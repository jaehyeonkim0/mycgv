package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.model.Board;
import com.springboot.mycgv.repository.BoardRepository;
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

    @Test
    @DisplayName("ModelMapper Test")
    public void convert() {

        Optional<Board> result = boardRepository.findById(42L);


        BoardDto boardDto = modelMapper.map(result, BoardDto.class);

        log.info("게시판 = {}", boardDto.getClass());
        log.info("게시판 = {}", result.getClass());
    }

}