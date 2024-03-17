package com.springboot.mycgv.controller;

import com.springboot.mycgv.config.mapstruct.ModelMapStruct;
import com.springboot.mycgv.repository.BoardRepository;
import com.springboot.mycgv.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class BoardControllerTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ModelMapStruct modelMapStruct;

//    @Test
//    @DisplayName("게시판 목록 조회")
//    @Transactional
//    void boardIndexTest() {
//        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "bid"));
//        String searchText = "";
//
//        Page<Board> list = boardRepository.findByBtitleContainingOrBcontentContaining(searchText, searchText, pageable);
//        List<Board> boardList = list.toList();
//
//    }

}