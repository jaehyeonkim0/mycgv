package com.springboot.mycgv.repository;

import com.springboot.mycgv.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reservation, Long> {

}
