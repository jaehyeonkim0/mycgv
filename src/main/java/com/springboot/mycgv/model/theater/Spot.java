package com.springboot.mycgv.model.theater;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "mycgv_spot")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String spotName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localarea_id")
    private LocalArea localArea;

//    @OneToMany(mappedBy = "spot")
//    private Set<MovieSpot> movieSpotSet = new HashSet<>();

    @Builder
    public Spot(String spotName, LocalArea localArea) {
        this.spotName = spotName;
        this.localArea = localArea;
    }

}
