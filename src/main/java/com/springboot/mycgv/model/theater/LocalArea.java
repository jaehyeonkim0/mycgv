package com.springboot.mycgv.model.theater;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "mycgv_localarea")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocalArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "localArea")
    private Set<Spot> spotSet = new HashSet<>();

    public LocalArea(String name) {
        this.name = name;
    }

    public void addSpot(Spot spot) {
        this.spotSet.add(spot);
    }

}
