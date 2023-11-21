package com.springboot.mycgv.repository;

import com.springboot.mycgv.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    //update mycgv_board set bhits = bhits + 1 where id = ?
    @Modifying
    @Query(value = "UPDATE Board b SET b.bhits = b.bhits + 1 WHERE b.bid =:bid")
    void updateHits(@Param("bid") Long bid);

    Page<Board> findByBtitleContainingOrBcontentContaining(String btitle, String bcontent, Pageable pageable);

    Board findOneByBid(Long bid);


    @EntityGraph(attributePaths = {"boardImageList"})
    @Query(value = "SELECT b FROM Board b WHERE b.bid=:bid")
    Optional<Board> findByIdWithBoardImage(@Param("bid") Long bid);

}
