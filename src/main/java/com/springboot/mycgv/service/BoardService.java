package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.model.board.Board;
import com.springboot.mycgv.repository.BoardRepository;
import com.springboot.mycgv.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

//todo Service 계층에서 DTO 변환 끝내기
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardService {

    private final FileUploadService fileUploadService;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시판 리스트 생성
     * @param pageable 페이지 정보
     * @param searchText 검색어
     * @return 제목 or 내용에 검색어를 포함한 게시판 리스트 목록
     */
    @Transactional
    public Page<Board> listBoard(Pageable pageable, String searchText) {
        Page<Board> boardResult =
                boardRepository.findByBtitleContainingOrBcontentContainingOrMemberId(searchText,searchText,searchText,pageable);

        return boardResult;
    }

    /**
     * 게시물 작성
     * @param boardDto 폼 데이터
     * @throws IOException
     */
    public void saveBoard(BoardDto boardDto) throws IOException {
        boardDto.setMember(memberRepository.findOneById(boardDto.getId()));
        Board board = boardDto.toEntity();
        boardRepository.save(board);

        boardDto.putStoredImageNameList(board); //set storedImageName
        fileUploadService.imageSave(boardDto);

    }

    /**
     * 게시판 수정
     * @param boardDto 폼 데이터
     * @throws IOException
     */
    public void modifyBoard(BoardDto boardDto) throws IOException {
        if(boardDto.getStoredImageNameList() != null) {
            fileUploadService.imageDelete(boardDto);
            Board board = boardRepository.findById(boardDto.getBid()).orElseThrow();

            board.clearImage();
        }
        saveBoard(boardDto);
    }

    /**
     * 게시판 상세보기
     * @param bid 게시글 id
     * @return (List<MultipartFile> 포함) boardDto
     */
    public BoardDto detailBoard(Long bid) {
        Optional<Board> optionalBoardEntity = boardRepository.findById(bid);

        if(optionalBoardEntity.isPresent()) {
            Board board = optionalBoardEntity.get();
            log.info("현재 boardImageList size = {}", board.getBoardImageList().size());
            BoardDto boardDto = BoardDto.toDetailBoardDto(board);
            return boardDto;
        }else {
            return null;
        }
    }

    /**
     * 게시판 조회수 증가
     * @param bid 게시글 id
     */
    @Transactional
    public void updateHits(Long bid) {
        boardRepository.updateHits(bid);
    }


    /**
     * 게시글 삭제
     * @param bid 게시글 id
     */
    public void deleteBoard(Long bid) throws IOException{
        BoardDto boardDto =
                BoardDto.toDetailBoardDto(boardRepository.findOneByBid(bid));

        if(boardDto.getFileAttached() == 1) {
            fileUploadService.imageDelete(boardDto);
        }
        boardRepository.deleteById(bid);
    }














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

//    public String getBsfile(String bid) {
//        return boardMapper.getBsfile(bid);
//    }

//    public int delete(String bid) {
//        return boardMapper.delete(bid);
//    }



}
