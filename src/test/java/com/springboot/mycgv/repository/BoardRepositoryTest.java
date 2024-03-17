package com.springboot.mycgv.repository;

import com.springboot.mycgv.enums.MemberRole;
import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.model.board.Board;
import com.springboot.mycgv.model.board.comment.Comment;
import com.springboot.mycgv.model.board.images.BoardImage;
import com.springboot.mycgv.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @DisplayName("게시판 저장")
    void saveBoard() {
        Member member = memberRepository.findOneById("test");

        Board board = Board.builder()
                .btitle("제목1")
                .bcontent("내용1")
                .build();
        board.addMember(member);

        boardRepository.save(board);
    }

    @Test
    @DisplayName("게시판 조회")
    void getBoard() {
        Board board = boardRepository.findById(10L).get();

        List<BoardImage> boardImageList = board.getBoardImageList();

        Member member = board.getMember();

        List<Comment> comments = board.getCommentEntityList();
    }

    @Test
    @DisplayName("양방향 테스트")
    void saveMemberBoard() {


        Member member = Member.builder()
                .id("test5")
                .password(bCryptPasswordEncoder.encode("123"))
                .name("테스트5")
                .email("test5@naver.com")
                .build();
        member.addRole(MemberRole.USER);

        memberRepository.save(member);
        Board board = Board.builder()
                .btitle("제목6")
                .bcontent("내용6")
                .build();

        member.getLists().add(board);
        board.addMember(member);

        boardRepository.save(board);
        List<Board> list = member.getLists();
        log.info("list size = {}",list.size());
    }

}