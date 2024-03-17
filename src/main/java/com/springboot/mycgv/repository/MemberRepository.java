package com.springboot.mycgv.repository;

import com.springboot.mycgv.model.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Member findOneById(String id);

    boolean existsById(String id);

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.id = :id")
    Optional<Member> getWithRoles(@Param("id") String id);

    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(String email);

//    @EntityGraph(attributePaths = "roleSet")
//    Optional<Member> findById(String id);

    Optional<Member> findAllById(String id);
}
