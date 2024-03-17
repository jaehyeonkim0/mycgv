package com.springboot.mycgv.model;

import com.springboot.mycgv.model.movie.Movie;
import com.springboot.mycgv.model.theater.Spot;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "mycgv_reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long rid;

    @Column
    private int headCount;

    @Column(name = "reservationTime")
    private LocalDate rTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id")
    private Spot spot;

    @Column
    private int amount;


    @Builder
    public Reservation(int headCount, LocalDate rTime, Member member,
                       Movie movie, Spot spot, int amount) {
        this.headCount = headCount;
        this.rTime = rTime;
        this.member = member;
        this.movie = movie;
        this.spot = spot;
        this.amount = amount;
    }
}
