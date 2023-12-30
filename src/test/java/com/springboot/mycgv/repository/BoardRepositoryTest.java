package com.springboot.mycgv.repository;

import com.springboot.mycgv.model.Board;
import com.springboot.mycgv.model.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsertWithImages() {

        for(int i=0; i<30; i++) {
            Board board = Board.builder()
                    .btitle("게시판 입력 테스트 " + i)
                    .bcontent("게시판 입력 테스트 " + i)
                    .member(Member.builder().id("test").build())
                    .fileAttached(0)
                    .build();

            boardRepository.save(board);
        }


//        for(int i=0;i<5;i++) {
//            board.addImage(UUID.randomUUID().toString(), "file"+i+".jpg");
//        }


    }

    @Test
    @DisplayName(value = "Fetch Test")
    public void FetchTest() {

        Optional<Board> board = boardRepository.findById(41L);

        Board board1 = board.orElseThrow();

        log.info(board1);

    }

    @Test
    public void indexOfTest() {

        String str = "To be, or not to be, that is the question.";
        char searchValue = 'e';
        int fromIndex = str.indexOf(searchValue);
        log.info("fromIndex = {}", fromIndex);

    }




    @Test
    public void testReadImages() {


        long startTime = System.currentTimeMillis();

        Optional<Board> result = boardRepository.findByIdWithBoardImage(42L);

        Board board = result.orElseThrow();

        log.info("Response Time = {}ms board = {}",
                (System.currentTimeMillis()-startTime), board);


//        log.info(board);
//        log.info("-------------------------------------------------");
//
//        for (BoardImage boardImage : board.getBoardImageList()) {
//            log.info(boardImage);
//        }


    }

}