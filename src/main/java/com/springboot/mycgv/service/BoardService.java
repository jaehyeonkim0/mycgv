package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.model.Board;
import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.repository.BoardMapper;
import com.springboot.mycgv.repository.BoardRepository;
import com.springboot.mycgv.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

//    public List<BoardDto> list(PageDto pageDto) {
//        return boardMapper.list(pageDto);
//    }


//    public BoardDto content(String bid) {
//        return boardMapper.content(bid);
//    }

//    public int insert(BoardDto boardDto) {
//        return boardMapper.insert(boardDto);
//    }
//    public int update(BoardDto boardDto) {
//        return boardMapper.update(boardDto);
//    }

    public String getBsfile(String bid) {
        return boardMapper.getBsfile(bid);
    }

    public int delete(String bid) {
        return boardMapper.delete(bid);
    }


    //------------------------------------------------------------------------------------

    public void insert(BoardDto boardDto, String id) {
        boardDto.setMember(memberRepository.findOneById(id));
        boardRepository.save(boardDto.toEntity());
    }

    public BoardDto update(BoardDto boardDto) {
        boardDto.setMember(memberRepository.findOneById(boardDto.getId()));
        Board updateBoardEntity = Board.toUpdateEntity(boardDto);
        boardRepository.save(updateBoardEntity);
        return content(boardDto.getBid());
    }

    public List<BoardDto> list() {
        List<Board> boardResult = boardRepository.findAll(Sort.by(Sort.Direction.ASC, "updatedTime"));

        List<BoardDto> boardDto = new ArrayList<>();
        for(Board board : boardResult) {
            boardDto.add(BoardDto.toBoardDto(board));
        }
        return boardDto;
    }

    public BoardDto content(Long bid) {
        Optional<Board> optionalBoardEntity = boardRepository.findById(bid);

        if(optionalBoardEntity.isPresent()) {
            Board board = optionalBoardEntity.get();

            BoardDto boardDto = BoardDto.toBoardDto(board);
            Member member = memberRepository.findOneById(board.getMember().getId());
            boardDto.setMember(member);
            return boardDto;
        }else {
            return null;
        }
    }

    @Transactional
    public void updateHits(Long bid) {
        boardRepository.updateHits(bid);
    }



//    public Page<BoardDto> paging(Pageable pageable) {
//        int page = pageable.getPageNumber() - 1;
//        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
//        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
//        // page 위치에 있는 값은 0부터 시작
//        Page<Board> boardEntities = boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "bid")));
//
//        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
//        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
//        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
//        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
//        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
//        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
//        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
//        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부
//
//        // 목록: id, writer, title, hits, createdTime
//        Page<BoardDto> boardDTOS =
//                boardEntities.map(board -> BoardDto.builder()
//                        .id(board.getId())
//                        .bid(board.getBid())
//                        .btitle(board.getBtitle())
//                        .bhits(board.getBhits())
//                        .bdate(board.getBdate())
//                        .build());
//
//        return boardDTOS;
//    }
}
