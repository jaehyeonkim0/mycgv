package com.springboot.mycgv.service;

import com.springboot.mycgv.model.theater.LocalArea;
import com.springboot.mycgv.model.theater.Spot;
import com.springboot.mycgv.repository.LocalRepository;
import com.springboot.mycgv.repository.MovieRepository;
import com.springboot.mycgv.repository.MovieSpotRepository;
import com.springboot.mycgv.repository.SpotRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Log4j2
class MovieServiceTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieSpotRepository movieSpotRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private LocalRepository localRepository;

    @Test
    void insertTest() {

//        Movie movie = Movie.builder()
//                .movieName("RING")
//                .ageLimit("12")
//                .startDate("2023/04/12")
//                .endDate("2024/04/23")
//                .build();
//        movieRepository.save(movie);

        LocalArea localArea = localRepository.findById(1L).orElseThrow();
//        localRepository.save(localArea);

        for(int i=1; i<10; i++) {
            Spot spot = Spot.builder()
                    .spotName("GANG_NAM" + i)
                    .localArea(localArea)
                    .build();
            spotRepository.save(spot);
        }


//        MovieSpot movieSpot = new MovieSpot(spot, movie);
//        movieSpotRepository.save(movieSpot);




    }

    @Test
    void reserveTest() {

    }

}