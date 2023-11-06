package com.springboot.mycgv.dto;

import com.springboot.mycgv.model.Board;
import com.springboot.mycgv.model.BoardFile;
import com.springboot.mycgv.model.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {

//    private String page;
//    private int rno;
    private Long bid;
    private Long fid;
    private String id;
    private Member member;

    private String btitle;
    private String bcontent;
    private int bhits;

    private LocalDate boardCreatedTime;
    private LocalDate boardUpdatedTime;

    private PageDto pageDto;
    private MultipartFile boardDtoFile;     // 실제 파일을 담을 수 있게 해줌
                                            // 이전까지는 텍스트 값만 DTO에 담았음
                                            // view -> controller 넘어갈 때 파일을 담는 용도

    private String originalFileName;        // 원본 파일 이름
    private String saveFileName;            // 서버 저장용 파일 이름
    private int fileAttached;               // 파일 첨부 여부(첨부 : 1, 미첨부 : 0) boolean값으로 하면 entity에서 다룰게 많음

    private String fileNameBeforeUpdate;    // 이전 파일명



    private List<MultipartFile> boardDtoFile1;
    private List<String> originalFileName1;
    private List<String> saveFileName1;
    private List<String> fileNameBeforeUpdate1;

    private String isNewFile;
    private PageDto2 pageDto2;


    public Board toEntity() {
        return Board.builder()
                .bid(bid)
                .btitle(btitle)
                .bcontent(bcontent)
                .bhits(bhits)
                .member(member)
                .fileAttached(0)
                .build();
    }

    public Board toFileEntity() {
        return Board.builder()
                .bid(bid)
                .btitle(btitle)
                .bcontent(bcontent)
                .bhits(bhits)
                .member(member)
                .fileAttached(1)
                .build();
    }

//    public static BoardDto toBoardDto(Board board) {
//        BoardDto boardDto = new BoardDto();
//        boardDto.setBid(board.getBid());
//        boardDto.setBtitle(board.getBtitle());
//        boardDto.setBcontent(board.getBcontent());
//        boardDto.setBhits(board.getBhits());
//        boardDto.setMember(board.getMember());
//        boardDto.setId(board.getMember().getId());
//        boardDto.setBoardCreatedTime(board.getCreatedTime());
//        boardDto.setBoardUpdatedTime(board.getUpdatedTime());
//
//        if(board.getFileAttached() == 0) {
//            boardDto.setFileAttached(0);
//        }else {
//            boardDto.setFileAttached(1);
//            boardDto.setOriginalFileName(board.getBoardFileList().get(0).getOriginalFileName());
//            boardDto.setSaveFileName(board.getBoardFileList().get(0).getSaveFileName());
//        }
//
//        return boardDto;
//    }

    public static BoardDto toMultipleFileBoardDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setBid(board.getBid());
        boardDto.setBtitle(board.getBtitle());
        boardDto.setBcontent(board.getBcontent());
        boardDto.setBhits(board.getBhits());
        boardDto.setMember(board.getMember());
        boardDto.setId(board.getMember().getId());
        boardDto.setBoardCreatedTime(board.getCreatedTime());
        boardDto.setBoardUpdatedTime(board.getUpdatedTime());

        if(board.getFileAttached() == 0) {
            boardDto.setFileAttached(0);
        }else {
            List<String> originalFileNameList = new ArrayList<>();
            List<String> saveFileNameList = new ArrayList<>();
            boardDto.setFileAttached(1);

            for(BoardFile boardFile : board.getBoardFileList()) {
                originalFileNameList.add(boardFile.getOriginalFileName());
                saveFileNameList.add(boardFile.getSaveFileName());
            }

            boardDto.setOriginalFileName1(originalFileNameList);
            boardDto.setSaveFileName1(saveFileNameList);
        }
        return boardDto;
    }


//    public static void toBoardFileEntity(BoardDto boardDto, Board board) {
//
//        if(boardDto.getBoardDtoFile1().size() > boardDto.getFileNameBeforeUpdate1().size()) {
//            //신규 파일 업로드 갯수 > 기존 파일 업로드 갯수
//            BoardFile.toUpdateMultipleBoardFileEntity(boardDto.toFileEntity(),
//                    boardDto.getOriginalFileName1(), boardDto.getSaveFileName1());
//        }else {
//            int differenceBetweenNewAndOld = boardDto.getFileNameBeforeUpdate1().size() - boardDto.getBoardDtoFile1().size();
//
//            BoardFile.toUpdateMultipleBoardFileEntity(boardDto.toFileEntity(),
//                    boardDto.getOriginalFileName1(), boardDto.getSaveFileName1());
//
//            for(int i=0; i<differenceBetweenNewAndOld; i++) {
//                BoardFile.builder()
//                        .fid(board.getBoardFileList().get(i).getFid())
//                        .fileShowOrNot(0)
//                        .build();
//            }
//        }
//
//
//
//    }



}
