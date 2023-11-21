package com.springboot.mycgv.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@SequenceGenerator(
        name = "COMMENT_SEQ_GENERATOR",
        sequenceName = "SEQU_MYCGV_COMMENT", // 매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 1)

@Table(name = "mycgv_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "board")
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
    @JoinColumn(name = "board_bid")
    private Board board;


    @Builder
    public Comment (Long cid, String commentWriter, String commentContents, Board board) {
        this.cid = cid;
        this.commentWriter = commentWriter;
        this.commentContents = commentContents;
        this.board = board;
    }

    public void changeComment(Board board) {
        this.board = board;
    }
}