package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.BoardDto;
import com.springboot.mycgv.model.Board;
import com.springboot.mycgv.model.BoardFile;
import com.springboot.mycgv.repository.BoardFileRepository;
import com.springboot.mycgv.repository.BoardMapper;
import com.springboot.mycgv.repository.BoardRepository;
import com.springboot.mycgv.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BoardService {

    private FileUploadService fileUploadService;
    private BoardMapper boardMapper;
    private BoardRepository boardRepository;
    private MemberRepository memberRepository;
    private BoardFileRepository boardFileRepository;

//    public Page<Board> list(Pageable pageable) {
//        Page<Board> boardResult = boardRepository.findAll(pageable);
//
//        return boardResult;
//    }

//    public List<BoardDto> list() {
//        List<Board> boardResult = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "bid"));
//
//        List<BoardDto> boardDto = new ArrayList<>();
//        for(Board board : boardResult) {
//            boardDto.add(BoardDto.toBoardDto(board));
//        }
//        return boardDto;
//    }

    /**
     * 게시판 리스트 생성
     * @param pageable 페이지 정보
     * @param searchText 검색어
     * @return 제목 or 내용에 검색어를 포함한 게시판 리스트 목록
     */
    public Page<Board> list(Pageable pageable, String searchText) {
        Page<Board> boardResult = boardRepository.findByBtitleContainingOrBcontentContaining(searchText,searchText, pageable);

        return boardResult;
    }

    /**
     * 단일 파일 업로드 게시판 작성
     * @param boardDto (MultipartFile 포함) 폼 데이터
     * @throws IOException
     */
    public void insert(BoardDto boardDto) throws IOException {
        boardDto = (BoardDto) fileUploadService.fileCheck(boardDto); //파일있는지 없는지 확인
        boardDto.setMember(memberRepository.findOneById(boardDto.getId()));

        if(!boardDto.getOriginalFileName().equals("")
                && boardDto.getOriginalFileName() != null) {
            //파일이 있으면
            Board board = boardRepository.save(boardDto.toFileEntity());
            fileUploadService.fileSave(boardDto);
            Long savedId = boardRepository.save(board).getBid();
            Board board2 = boardRepository.findById(savedId).get();

            BoardFile boardFile =
                    BoardFile.toFileEntity(board2, boardDto.getOriginalFileName(), boardDto.getSaveFileName());
            boardFileRepository.save(boardFile);
        }else {
            //파일 없으면
            boardRepository.save(boardDto.toEntity());
        }
    }

    /**
     * 다중 파일 업로드 게시판 작성
     * @param boardDto (List<MultipartFile> 포함) 폼 데이터
     * @throws IOException
     */

    public void multipleInsert(BoardDto boardDto) throws IOException {

        boardDto = (BoardDto) fileUploadService.multipleFileCheck(boardDto); //파일있는지 없는지 확인
        boardDto.setMember(memberRepository.findOneById(boardDto.getId()));

        if(boardDto.getOriginalFileName1() != null) { //파일이 null이 아니고 하나라도 파일명이 있다면
            log.info("파일있음");
            Board board = boardRepository.save(boardDto.toFileEntity()); //btitle, bcontent 저장
            fileUploadService.mutipleFileSave(boardDto);
            Long savedId = board.getBid();
            Board board2 = boardRepository.findById(savedId).get();

            for(int i=0; i<boardDto.getBoardDtoFile1().size(); i++) {
                BoardFile boardFile =
                        BoardFile.toFileEntity(board2,
                                boardDto.getOriginalFileName1().get(i), boardDto.getSaveFileName1().get(i));

                boardFileRepository.save(boardFile);
            }
        }else {
            //파일 없으면
            boardRepository.save(boardDto.toEntity());
        }
    }

    /**
     * 단일 파일 업로드 게시판 상세보기
     * @param bid 게시글 id
     * @return (MultipartFile 포함) boardDto
     */
//    public BoardDto content(Long bid) {
//        Optional<Board> optionalBoardEntity = boardRepository.findById(bid);
//
//        if(optionalBoardEntity.isPresent()) {
//            Board board = optionalBoardEntity.get();
//
//            BoardDto boardDto = BoardDto.toBoardDto(board);
//            return boardDto;
//        }else {
//            return null;
//        }
//    }

    /**
     * 다중 파일 업로드 게시판 상세보기
     * @param bid 게시글 id
     * @return (List<MultipartFile> 포함) boardDto
     */
    public BoardDto multipleContent(Long bid) {
        Optional<Board> optionalBoardEntity = boardRepository.findById(bid);

        if(optionalBoardEntity.isPresent()) {
            Board board = optionalBoardEntity.get();

            BoardDto boardDto = BoardDto.toMultipleFileBoardDto(board);
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
     * 단일 파일 게시판 수정
     * @param boardDto (MultipartFile 포함) 폼 데이터
     * @throws IOException
     */
//    public void update(BoardDto boardDto) throws IOException {
//        String fileNameBeforeUpdate = boardDto.getSaveFileName();
//        boardDto.setFileNameBeforeUpdate(fileNameBeforeUpdate); //기존 파일 이름
//
//        boardDto = (BoardDto) fileUploadService.fileCheck(boardDto);
//        //boardDto에는 이미 originalFileName, saveFileName 세팅 완료
//        boardDto.setMember(memberRepository.findOneById(boardDto.getId()));
//
//        if(boardDto.getFileNameBeforeUpdate() != null) {
//            //1. 이전에 업로드한 파일명이 있을 시
//            if(!boardDto.getBoardDtoFile().isEmpty()) { //파일 있으면
//                //1. 파일 변경
//                fileUploadService.fileDelete(boardDto.getFileNameBeforeUpdate());
//                fileUploadService.fileSave(boardDto);
//
//                boardFileRepository.save(
//                        BoardFile.toUpdateBoardFileEntity(
//                                boardRepository.findOneByBid(boardDto.getBid()),
//                                boardDto.getOriginalFileName(),
//                                boardDto.getSaveFileName()
//                        )
//                );
//                boardRepository.save(boardDto.toFileEntity()); //btitle, bcontent 수정
//            }else {
//                //2. 파일 삭제
//                fileUploadService.fileDelete(boardDto.getFileNameBeforeUpdate());
//                boardRepository.save(boardDto.toEntity());
//            }
//        }else {
//            //2. 이전에 업로드한 파일명이 없을 시
//            if(!boardDto.getBoardDtoFile().isEmpty()) {
//                //원래 파일 없었다가 파일 생성
//                fileUploadService.fileSave(boardDto); //디렉토리 파일 저장
//                boardRepository.save(boardDto.toFileEntity()); //fileAttached 1 변경
//
//                //BoardFile에 저장해야됨
//                boardFileRepository.save(
//                        BoardFile.toFileEntity(
//                                boardRepository.findOneByBid(boardDto.getBid()),
//                                boardDto.getOriginalFileName(),
//                                boardDto.getSaveFileName()
//                        )
//                );
//            }else {
//                //원래 파일 없었을 때 파일 없음 유지
//                boardRepository.save(boardDto.toEntity());
//            }
//        }
//    }

    /**
     * 다중 파일 게시판 수정
     * @param boardDto (List<MultipartFile> 포함) 폼 데이터
     * @throws IOException
     */
    public void multipleUpdate(BoardDto boardDto) throws IOException {

        boardDto = (BoardDto) fileUploadService.multipleFileCheck(boardDto);
        //boardDto에는 이미 originalFileName, saveFileName 세팅 완료
        boardDto.setMember(memberRepository.findOneById(boardDto.getId()));

        if(boardDto.getFileNameBeforeUpdate1() != null) {
            //1. 이전에 업로드한 파일명이 1개 이상일 경우
            if(boardDto.getBoardDtoFile1() != null 
                    || boardDto.getBoardDtoFile1().size() > 0) { //파일 있으면
                //1. 파일 변경
                fileUploadService.multipleFileDelete(boardDto); //이전 업로드 파일 삭제(x)
                fileUploadService.mutipleFileSave(boardDto); //새로 올린 파일들 저장(o)

                if(boardDto.getBoardDtoFile1().size() == boardDto.getFileNameBeforeUpdate1().size()) {
                    //(1)기존 올렸던 파일 갯수 = 새로 올리는 파일 갯수
                    //BoardFile에 저장
                    boardFileRepository.saveAll(
                            BoardFile.toUpdateMultipleBoardFileEntity(
                                    boardRepository.findOneByBid(boardDto.getBid()),
                                    boardDto.getOriginalFileName1(),
                                    boardDto.getSaveFileName1()
                            )
                    );
                }else if(boardDto.getFileNameBeforeUpdate1().size() > boardDto.getBoardDtoFile1().size()){
                    log.info("기존 업로드 파일 갯수 > 신규 업로드 파일 갯수");
                }




                boardRepository.save(boardDto.toFileEntity()); //btitle, bcontent 수정

                //(2)기존 올렸던 파일 갯수 > 새로 올리는 파일 갯수





                //(3)기존 올렸던 파일 갯수 < 새로 올리는 파일 갯수



            }else {
                //2. 파일 삭제
                fileUploadService.multipleFileDelete(boardDto);
                boardRepository.save(boardDto.toEntity());
            }
        }else {
            //문제 없음
            //2. 이전에 업로드한 파일명이 없을 시 == 기존 올렸던 파일 갯수 < 새로 올리는 파일 갯수

            if(boardDto.getBoardDtoFile1().size() > 0) {
                //원래 파일 없었다가 파일 생성
                fileUploadService.mutipleFileSave(boardDto); //디렉토리 파일 저장
                boardRepository.save(boardDto.toFileEntity()); //fileAttached 1 변경

                for(int i=0; i<boardDto.getBoardDtoFile1().size(); i++) {
                    boardFileRepository.save(
                            BoardFile.toUpdateBoardFileEntity(
                                    boardRepository.findOneByBid(boardDto.getBid()),
                                    boardDto.getOriginalFileName1().get(i),
                                    boardDto.getSaveFileName1().get(i)
                            )
                    );
                }
            }else {
                //원래 파일 없었을 때 파일 없음 유지
                boardRepository.save(boardDto.toEntity());
            }
        }
    }



    /**
     * 게시글 삭제
     * @param bid 게시글 id
     */
    public void delete(Long bid) {

        Board fileExistCheck = boardRepository.findOneByBid(bid);
        if(fileExistCheck.getFileAttached() == 1) {
            //파일 있는 경우
            for(int i=0; i<fileExistCheck.getBoardFileList().size();i++) {
                Long boardFileFidList = boardRepository.findById(bid).get().getBoardFileList().get(i).getFid();
                String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\upload\\";
                File deleteFile = new File(projectPath+boardFileRepository.findById(boardFileFidList).get().getSaveFileName());
                if(deleteFile.exists()) {
                    deleteFile.delete();
                }
            }
        }
        boardRepository.deleteById(bid);
    }

/**------------------------------------------------------------------------------------*/
//    public void insert(BoardDto boardDto, String id) throws IOException {
//
//        if(boardDto.getBoardDtoFile().isEmpty()) {
//            //첨부 파일 없는 경우
//            boardDto.setMember(memberRepository.findOneById(id));
//            boardRepository.save(boardDto.toEntity());
//        }else {
//            //첨부 파일 있는 경우
//            /*
//                1. DTO에 담긴 파일을 꺼냄
//                2. 파일의 이름 가져옴
//                3. 서버 저장용 이름을 만듦
//                // 내사진.jpg => 839798375892_내사진.jpg
//                4. 저장 경로 설정
//                5. 해당 경로에 파일 저장
//                6. MYCGV_BOARD에 해당 데이터 save 처리
//                7. board_file_table에 해당 데이터 save 처리
//             */
//            MultipartFile boardDtoFile = boardDto.getBoardDtoFile(); // 1.
//            String originalFileName = boardDtoFile.getOriginalFilename(); // 2.
//            String saveFileName = System.currentTimeMillis() + "_" + originalFileName; // 3.
//            String savePath = "C:\\dev\\mycgv\\src\\main\\resources\\static\\upload\\" + saveFileName; // 4. C:/springboot_img/9802398403948_내사진.jpg
//            boardDtoFile.transferTo(new File(savePath)); //File(java.io) boardFile을 savePath로 넘긴다(transferTo) // 5.
//
//            boardDto.setMember(memberRepository.findOneById(id));
//            Board board = boardRepository.save(boardDto.toFileEntity());
//
//            Long savedId = boardRepository.save(board).getBid();
//            Board board2 = boardRepository.findById(savedId).get();
//
//            BoardFile boardFile = BoardFile.toFileEntity(board2, originalFileName, saveFileName);
//            boardFileRepository.save(boardFile);
//        }
//
//    }

    //    public void insert(BoardDto boardDto) throws IOException {
//
//        if(boardDto.getBoardDtoFile().isEmpty()) {
//            //첨부 파일 없는 경우
//            boardDto.setMember(memberRepository.findOneById(boardDto.getId()));
//            boardRepository.save(boardDto.toEntity());
//        }else {
//            //첨부 파일 있는 경우
//            /*
//                1. DTO에 담긴 파일을 꺼냄
//                2. 파일의 이름 가져옴
//                3. 서버 저장용 이름을 만듦
//                // 내사진.jpg => 839798375892_내사진.jpg
//                4. 저장 경로 설정
//                5. 해당 경로에 파일 저장
//                6. MYCGV_BOARD에 해당 데이터 save 처리
//                7. board_file_table에 해당 데이터 save 처리
//             */
//            MultipartFile boardDtoFile = boardDto.getBoardDtoFile(); // 1.
//            String originalFileName = boardDtoFile.getOriginalFilename(); // 2.
//            String saveFileName = System.currentTimeMillis() + "_" + originalFileName; // 3.
//            String savePath = "C:\\dev\\mycgv\\src\\main\\resources\\static\\upload\\" + saveFileName; // 4. C:/springboot_img/9802398403948_내사진.jpg
//            boardDtoFile.transferTo(new File(savePath)); //File(java.io) boardFile을 savePath로 넘긴다(transferTo) // 5.
//
//            boardDto.setMember(memberRepository.findOneById(boardDto.getId()));
//            Board board = boardRepository.save(boardDto.toFileEntity());
//
//            Long savedId = boardRepository.save(board).getBid();
//            Board board2 = boardRepository.findById(savedId).get();
//
//            BoardFile boardFile = BoardFile.toFileEntity(board2, originalFileName, saveFileName);
//            boardFileRepository.save(boardFile);
//        }
//
//    }

    /**------------------------------------------------------------------------------------*/




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
