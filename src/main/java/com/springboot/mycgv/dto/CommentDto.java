package com.springboot.mycgv.dto;

import com.springboot.mycgv.model.Board;
import com.springboot.mycgv.model.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class CommentDto {

    private Long cid;
    private String commentWriter;

    @NotBlank(message = "댓글은 최소 4자 이상으로 작성하세요")
    private String commentContents;

    private LocalDate commentCreatedTime;
    private PageDto2 pageDto2;

    private Long bid;


    public static CommentDto toCommentDTO(Comment comment, Long bid) {
        CommentDto commentDto = new CommentDto();
        commentDto.setCid(comment.getCid());
        commentDto.setCommentWriter(comment.getCommentWriter());
        commentDto.setCommentContents(comment.getCommentContents());
        commentDto.setCommentCreatedTime(comment.getCreatedTime());
        commentDto.setBid(bid);
        return commentDto;
    }

    public Comment toEntity(CommentDto commentDto, Board board) {
        Comment comment = Comment.builder()
                .commentWriter(commentDto.getCommentWriter())
                .commentContents(commentDto.getCommentContents())
                .board(board)
                .build();

        return comment;
    }
}
