package com.springboot.mycgv.repository;

import com.springboot.mycgv.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

//    @EntityGraph(attributePaths = {"movieType"})
//    @Query("SELECT m FROM Movie m WHERE m.movieId =:id")
//    Optional<Movie> findByIdWithMovieType(@Param("id") Long id);
}
