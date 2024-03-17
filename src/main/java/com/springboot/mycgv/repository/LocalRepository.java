package com.springboot.mycgv.repository;

import com.springboot.mycgv.model.theater.LocalArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<LocalArea, Long> {
}
