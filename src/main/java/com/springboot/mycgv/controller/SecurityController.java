//package com.springboot.mycgv.controller;
//
//import com.springboot.mycgv.config.jwt.TokenProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("jwt")
//public class SecurityController {
//
//    private final TokenProvider tokenProvider;
//
//    @GetMapping("create")
//    public Map<String, Object> createToken(@RequestParam("id") String id) {
//        String token = tokenProvider.generateToken(id, (1*1000*60));
//        Map<String, Object> map = new HashMap<>();
//        map.put("result", token);
//        return map;
//    }
//
//    @GetMapping("get")
//    public Map<String, Object> getId(@RequestParam("token") String token) {
//        String id = tokenProvider.getId(token);
//        String email = tokenProvider.getEmail(token);
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", id);
//        map.put("email", email);
//        return map;
//    }
//
//}
