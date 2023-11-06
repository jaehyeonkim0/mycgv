package com.springboot.mycgv.model;

import com.springboot.mycgv.dto.BoardDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//얘가 연관관계 주인
/**
 * 연관관계 주인
 * */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR",
        sequenceName = "SEQU_MYCGV_BOARD", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)
@Table(name = "MYCGV_BOARD")
@ToString(exclude = "member")
public class Board extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "BOARD_SEQ_GENERATOR")
    private Long bid;

    @Column(name = "BTITLE", nullable = false)
    private String btitle;

    @Column(name = "BCONTENT", nullable = false)
    private String bcontent;

    @Column(name = "BHITS")
    private int bhits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private int fileAttached; // 1(파일 있음) or 0(파일 없음)

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    List<BoardFile> boardFileList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> commentEntityList = new ArrayList<>();

    @Builder
    public Board (Long bid, String btitle, String bcontent, int bhits,
                 Member member, int fileAttached) {
        this.bid = bid;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bhits = bhits;
        this.member = member;
        this.fileAttached = fileAttached;
    }

    public static Board toUpdateEntity(BoardDto boardDto) {
        Board board = Board.builder()
                .bid(boardDto.getBid())
                .btitle(boardDto.getBtitle())
                .bcontent(boardDto.getBcontent())
                .bhits(boardDto.getBhits())
                .member(boardDto.getMember())
                .fileAttached(0)
                .build();
        return board;
    }

    public static Board toUpdateFileEntity(BoardDto boardDto) {
        Board board = Board.builder()
                .bid(boardDto.getBid())
                .btitle(boardDto.getBtitle())
                .bcontent(boardDto.getBcontent())
                .bhits(boardDto.getBhits())
                .member(boardDto.getMember())
                .fileAttached(1)
                .build();
        return board;
    }
}
