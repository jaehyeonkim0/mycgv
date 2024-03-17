//package com.springboot.mycgv.controller;
//
//import com.springboot.mycgv.dto.MovieDto;
//import com.springboot.mycgv.service.MovieService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/reserve")
//@RequiredArgsConstructor
//public class ReserveController {
//
//    private final MovieService movieService;
//
//    @GetMapping("/home")
//    public void reserveGET(Model model) {
//        List<MovieDto> movieDtoList = movieService.getMovieList();
//        model.addAttribute("movieDtoList", movieDtoList);
//    }
//}
