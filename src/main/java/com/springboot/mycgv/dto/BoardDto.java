package com.springboot.mycgv.dto;

import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.model.board.Board;
import com.springboot.mycgv.model.board.images.BoardImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {

    private Long bid;
    private String id;
    private String writer;
    private Member member;

    @NotBlank(message = "제목은 필수 입력 항목입니다")
    private String btitle;

    @NotBlank(message = "내용은 필수 입력 항목입니다")
    private String bcontent;

    private int bhits;
    private int fileAttached = 0;

    private LocalDate boardCreatedTime;
    private LocalDate boardUpdatedTime;

    //boardImage
    private List<MultipartFile> boardImages;
    private String storedImageName;

    private List<String> originalImageNameList;
    private List<String> storedImageNameList;


    public Board toEntity() {
        Board board = Board.builder()
                .bid(bid)
                .btitle(btitle)
                .bcontent(bcontent)
                .bhits(bhits)
                .member(member)
                .fileAttached(fileAttached)
                .build();

        if(getBoardImages() != null &&
                !getBoardImages().get(0).getOriginalFilename().equals("")) {
            for(MultipartFile file : getBoardImages()) {
                board.addImage(UUID.randomUUID().toString(), file.getOriginalFilename());
            }
        }
        return board;
    }

    public void putStoredImageNameList(Board board) {
        storedImageNameList = new ArrayList<>();
        for(BoardImage boardImage : board.getBoardImageList()) {
            storedImageNameList.add(boardImage.getStoredImageName());
        }
    }


    public static BoardDto toBoardDtoList(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setBid(board.getBid());
        boardDto.setBtitle(board.getBtitle());
        boardDto.setBcontent(board.getBcontent());
        boardDto.setBhits(board.getBhits());
        boardDto.setId(board.getMember().getId());
        boardDto.setBoardCreatedTime(board.getCreatedTime());
        boardDto.setBoardUpdatedTime(board.getUpdatedTime());

        return boardDto;
    }

    public static BoardDto toDetailBoardDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setBid(board.getBid());
        boardDto.setBtitle(board.getBtitle());
        boardDto.setBcontent(board.getBcontent());
        boardDto.setBhits(board.getBhits());
        boardDto.setId(board.getMember().getId());
        boardDto.setBoardCreatedTime(board.getCreatedTime());
        boardDto.setBoardUpdatedTime(board.getUpdatedTime());

        if(board.getFileAttached() == 0) {
            boardDto.setFileAttached(0);
        }else {
            List<String> originalImageNames = new ArrayList<>();
            List<String> saveImageNames= new ArrayList<>();
            boardDto.setFileAttached(1);

            for(BoardImage boardImage : board.getBoardImageList()) {
                originalImageNames.add(boardImage.getOriginalImageName());
                saveImageNames.add(boardImage.getStoredImageName());
            }

            boardDto.setOriginalImageNameList(originalImageNames);
            boardDto.setStoredImageNameList(saveImageNames);
        }
        return boardDto;
    }

}
