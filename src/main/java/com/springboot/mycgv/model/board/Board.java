package com.springboot.mycgv.model.board;

import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.model.Time;
import com.springboot.mycgv.model.board.comment.Comment;
import com.springboot.mycgv.model.board.images.BoardImage;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mycgv_board")
@ToString(exclude = {"member", "commentEntityList", "boardImageList"})
public class Board extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private List<Comment> commentEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardImage> boardImageList = new ArrayList<>();

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

    @Builder
    public Board (Long bid, String btitle, String bcontent,
                  int bhits, int fileAttached) {
        this.bid = bid;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.bhits = bhits;
        this.fileAttached = fileAttached;
    }

    public void addImage(String uuid, String originalImageName) {
        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .originalImageName(originalImageName)
                .storedImageName(uuid + "_" + originalImageName)
                .board(this)
                .fileOrder(boardImageList.size())
                .build();
        this.boardImageList.add(boardImage);
        this.fileAttached = 1;
    }


    public void clearImage() {
        boardImageList.forEach(boardImage -> boardImage.changeBoard(null));
//        this.boardImageList.clear();
    }

    public void clearComment() {

        commentEntityList.forEach(comment -> comment.changeComment(null));

        this.commentEntityList.clear();

    }

    public void addMember(Member member) {
        this.member = member;
    }

}
