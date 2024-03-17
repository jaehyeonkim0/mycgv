package com.springboot.mycgv.repository;

import com.springboot.mycgv.model.board.Board;
import com.springboot.mycgv.model.board.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //    List<Comment> findAllByBoardOrderByCidDesc(Board board);
    Page<Comment> findAllByBoard(Board board, Pageable pageable);
}
