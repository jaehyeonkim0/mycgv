package com.springboot.mycgv.dto;

import com.springboot.mycgv.model.Board;
import com.springboot.mycgv.model.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BoardDto {

//    private String page;
//    private int rno;
    private Long bid;
    private String id;
    private Member member;
    private String btitle;
    private String bcontent;
    private int bhits;

    private LocalDate boardCreatedTime;
    private LocalDate boardUpdatedTime;
    private String bfile;
    private String bsfile;
//    private MultipartFile file1;


    public Board toEntity() {
        return Board.builder()
                .bid(bid)
                .btitle(btitle)
                .bcontent(bcontent)
                .bhits(bhits)
                .member(member)
                .bfile(bfile)
                .bsfile(bsfile)
                .build();
    }

    public static BoardDto toBoardDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setBid(board.getBid());
        boardDto.setBtitle(board.getBtitle());
        boardDto.setBcontent(board.getBcontent());
        boardDto.setBhits(board.getBhits());
        boardDto.setMember(board.getMember());
        boardDto.setId(board.getMember().getId());
        boardDto.setBoardCreatedTime(board.getCreatedTime());
        boardDto.setBoardUpdatedTime(board.getUpdatedTime());
        boardDto.setBfile(board.getBfile());
        boardDto.setBsfile(board.getBsfile());
        return boardDto;
    }



}
