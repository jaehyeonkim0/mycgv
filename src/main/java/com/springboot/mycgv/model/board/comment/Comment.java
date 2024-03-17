package com.springboot.mycgv.model.board.comment;

import com.springboot.mycgv.model.Time;
import com.springboot.mycgv.model.board.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "mycgv_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "board")
public class Comment extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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