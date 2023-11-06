package com.springboot.mycgv.service;

import com.springboot.mycgv.dto.CommentDto;
import com.springboot.mycgv.model.Board;
import com.springboot.mycgv.model.Comment;
import com.springboot.mycgv.repository.BoardRepository;
import com.springboot.mycgv.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDto commentDto) {
        /* 부모엔티티(BoardEntity) 조회 */
        Optional<Board> optionalBoardEntity = boardRepository.findById(commentDto.getBid());
        if (optionalBoardEntity.isPresent()) {
            Board board = optionalBoardEntity.get();
            return commentRepository.save(commentDto.toEntity(commentDto, board)).getCid();
        } else {
            return null;
        }
    }

//    public List<CommentDto> findAll(Long bid) {
//        Board board = boardRepository.findById(bid).get();
//        List<Comment> commentList = commentRepository.findAllByBoardOrderByCidDesc(board);
//        /* EntityList -> DTOList */
//        List<CommentDto> commentDtoList = new ArrayList<>();
//        for (Comment comment: commentList) {
//            CommentDto commentDto = CommentDto.toCommentDTO(comment, bid);
//            commentDtoList.add(commentDto);
//        }
//        return commentDtoList;
//    }

    public Page<Comment> findAll(Long bid, Pageable pageable) {
        Board board = boardRepository.findById(bid).get();
        Page<Comment> commentList = commentRepository.findAllByBoard(board, pageable);
        /* EntityList -> DTOList */


        return commentList;
    }
}
