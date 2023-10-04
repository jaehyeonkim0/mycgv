package com.springboot.mycgv.repository;

import com.springboot.mycgv.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    //update mycgv_board set bhits = bhits + 1 where id = ?
    @Modifying
    @Query(value = "UPDATE Board b SET b.bhits = b.bhits + 1 WHERE b.bid =:bid")
    void updateHits(@Param("bid") Long bid);

}
