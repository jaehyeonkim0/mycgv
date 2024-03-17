//package com.springboot.mycgv.service;
//
//import com.springboot.mycgv.config.mapstruct.ModelMapStruct;
//import com.springboot.mycgv.dto.MovieDto;
//import com.springboot.mycgv.model.movie.Movie;
//import com.springboot.mycgv.repository.MovieRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class MovieService {
//
//    private final MovieRepository movieRepository;
//    private final ModelMapStruct modelMapStruct;
//    public List<MovieDto> getMovieList() {
//
//        List<Movie> movieEntityList = movieRepository.findAll();
////        List<MovieDto> movieDtoList = movieList.stream().map(new Function<Movie, MovieDto>() {
////            @Override
////            public MovieDto apply(Movie movie) {
////                return modelMapStruct.toMovieDto(movie);
////            }
////        }).collect(Collectors.toList());
//
////        List<MovieDto> movieDtoList = movieEntityList.stream().map(movie ->
////                modelMapStruct.toMovieDto(movie)).collect(Collectors.toList());
//
//
//        return null;
//    }
//}
