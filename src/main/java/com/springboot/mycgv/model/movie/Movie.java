package com.springboot.mycgv.model.movie;

import com.springboot.mycgv.model.Reservation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * location : 지역 (서울, 경기..)
 * branch : 지점 (강남, 압구정 ..)
 * startDate : 영화 상영 시작날짜
 * endDate : 영화 상영 종료날짜
 * schedule : 주간, 야간
 * movieType : 2D, IMAX...
 */
@Entity
@Getter
@Table(name = "mycgv_movie")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long movieId;

    @Column(name = "name")
    private String movieName;

    @Column(name = "age")
    private String ageLimit;

    @Column
    private String startDate;

    @Column
    private String endDate;

    @OneToMany(mappedBy = "movie")
    private List<Reservation> reservationList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "mycgv_movie_type")
    private Set<MovieType> movieType = new HashSet<>();

//    다대다 연결이지만, 단방향으로 연결; 아직 다대다 확실히 알지 못함
//    @OneToMany(mappedBy = "movie")
//    private Set<MovieSpot> movieSpotSet = new HashSet<>();

    @Builder
    public Movie(String movieName, String ageLimit, String startDate, String endDate) {
        this.movieName = movieName;
        this.ageLimit = ageLimit;
        this.startDate = startDate;
        this.endDate = endDate;
    }

//    public void addMovieType(MovieType movieType) {
//        this.movieType.add(movieType);
//    }

}
