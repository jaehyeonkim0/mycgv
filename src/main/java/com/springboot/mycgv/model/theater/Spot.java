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
    @JoinColumn(name = "localarea_name")
    private LocalArea localArea;

//    @OneToMany(mappedBy = "spot")
//    private Set<MovieSpot> movieSpotSet = new HashSet<>();


    //todo branch @Id 필드 말고, branch_schedule 테이블 생성 시 name을
    // 외래키로 설정
//    @Enumerated(EnumType.STRING)
//    @ElementCollection(fetch = FetchType.LAZY, targetClass = TimeSchedule.class)
//    @CollectionTable(name = "spot_timeschedule", joinColumns = @JoinColumn(name = "spot_name"))
//    private Set<TimeSchedule> timeScheduleSet = new HashSet<>();

//    @Builder
//    public Spot(String name, LocalArea localArea) {
//        this.name = name;
//        this.localArea = localArea;
//    }
//
//    public void addSchedule(TimeSchedule timeSchedule) {
//        this.timeScheduleSet.add(timeSchedule);
//    }

    @Builder
    public Spot(String spotName, LocalArea localArea) {
        this.spotName = spotName;
        this.localArea = localArea;
    }

}
