package com.springboot.mycgv.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mycgv_board_image")
@ToString(exclude = "board")
public class BoardImage extends Time implements Comparable<BoardImage>{

    @Id
    private String uuid;

    @Column
    private String originalImageName;

    @Column
    private String storedImageName;

    @Column
    private int fileOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    public BoardImage(String uuid,
                      String originalImageName, String storedImageName,
                      Board board, int fileOrder) {
        this.uuid = uuid;
        this.originalImageName = originalImageName;
        this.storedImageName = storedImageName;
        this.board = board;
        this.fileOrder = fileOrder;
    }

    @Override
    public int compareTo(BoardImage other) {
        return this.fileOrder - other.fileOrder;
    }

    public void changeBoard(Board board) {
        this.board = board;
    }
}
