package com.springboot.mycgv.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MYCGV_BOARD_FILE")
@SequenceGenerator(
        name = "BOARD_FILE_SEQ_GENERATOR",
        sequenceName = "SEQU_MYCGV_BOARD_FILE", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
public class BoardFile extends Time{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "BOARD_FILE_SEQ_GENERATOR")
    private Long fid;

    @Column
    private String originalFileName;

    @Column
    private String saveFileName;

    @Column
    private int fileShowOrNot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public BoardFile (Long fid, Board board, String originalFileName, String saveFileName, int fileShowOrNot) {
        this.fid = fid;
        this.board = board;
        this.originalFileName = originalFileName;
        this.saveFileName = saveFileName;
        this.fileShowOrNot = fileShowOrNot;
    }

    public static BoardFile toFileEntity(Board board,
                                         String originalFileName, String saveFileName) {
        BoardFile boardFile = BoardFile.builder()
                .originalFileName(originalFileName)
                .saveFileName(saveFileName)
                .board(board)
                .fileShowOrNot(1)
                .build();
        return boardFile;
    }

    public static BoardFile toUpdateBoardFileEntity(Board board,
                                                    String originalFileName,
                                                    String saveFileName) {
        BoardFile boardFile = BoardFile.builder()
                .originalFileName(originalFileName)
                .saveFileName(saveFileName)
                .board(board)
                .fileShowOrNot(1)
                .build();

        return boardFile;
    }


    public static BoardFile toMultipleFileEntity(Board board,
                                                 String originalFileName,
                                                 String saveFileName) {
        BoardFile boardFile = BoardFile.builder()
                .originalFileName(originalFileName)
                .saveFileName(saveFileName)
                .board(board)
                .build();
        return boardFile;
    }

    public static List<BoardFile> toUpdateMultipleBoardFileEntity(Board board,
                                                                  List<String> originalFileName1,
                                                                  List<String> saveFileName1) {

        //기존 올렸던 파일과 새로 업로드하는 파일의 갯수가 다를 수도, 같을 수 있기 때문에 size 비교 후 다르게 로직 작성

        List<BoardFile> boardFileList = new ArrayList<>();

        //갯수가 같을 시
        for(int i=0;i< originalFileName1.size(); i++) {
            BoardFile boardFile = BoardFile.builder()
                    .originalFileName(originalFileName1.get(i))
                    .saveFileName(saveFileName1.get(i))
                    .board(board)
                    .fid(board.getBoardFileList().get(i).getFid())
                    .fileShowOrNot(1)
                    .build();

            boardFileList.add(boardFile);
        }
        return boardFileList;
    }


    public static List<BoardFile> toUpdateMultipleBoardFileEntity2(Board board,
                                                                  List<String> originalFileName1,
                                                                  List<String> saveFileName1) {

        List<BoardFile> boardFileList = new ArrayList<>();

        //갯수가 같을 시
        for(int i=0;i< originalFileName1.size(); i++) {
            BoardFile boardFile = BoardFile.builder()
                    .originalFileName(originalFileName1.get(i))
                    .saveFileName(saveFileName1.get(i))
                    .board(board)
                    .fid(board.getBoardFileList().get(i).getFid())
                    .fileShowOrNot(1)
                    .build();

            boardFileList.add(boardFile);
        }
        return boardFileList;
    }


}
