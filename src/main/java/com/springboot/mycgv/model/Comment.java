package com.springboot.mycgv.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@SequenceGenerator(
        name = "COMMENT_SEQ_GENERATOR",
        sequenceName = "SEQU_MYCGV_COMMENT", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)

@Table(name = "MYCGV_COMMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "COMMENT_SEQ_GENERATOR")
    private Long cid;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column(nullable = false)
    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


    @Builder
    public Comment (Long cid, String commentWriter, String commentContents, Board board) {
        this.cid = cid;
        this.commentWriter = commentWriter;
        this.commentContents = commentContents;
        this.board = board;
    }
}