package com.springboot.mycgv.repository;

import com.springboot.mycgv.model.MovieSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSpotRepository extends JpaRepository<MovieSpot, Long> {

}
