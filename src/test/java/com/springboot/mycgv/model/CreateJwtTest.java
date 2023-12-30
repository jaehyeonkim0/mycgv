//package com.springboot.mycgv.model;
//
//import com.springboot.mycgv.config.jwt.JwtProperties;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import javax.crypto.SecretKey;
//import java.util.Base64;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@Slf4j
//class CreateJwtTest {
//
//    @Autowired
//    private JwtProperties jwtProperties;
//
//
//    @Value(value = "${custom.jwt.secretkey}")
//    private String secretKeyPlan;
//
//    @Test
//    @DisplayName("JWT 비밀키 생성")
//    void createSecretKey() {
//        Assertions.assertThat(secretKeyPlan).isNotNull();
//
//        log.info("secretKey = {}", secretKeyPlan);
//    }
//
//    @Test
//    @DisplayName("sercretKey 원문으로 hmac 암호화 알고리즘에 맞는 SecretKey 객체를 만들 수 있다")
//    void secretKeyToEncoded() {
//        // 키를 Base64 인코딩
//        String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlan.getBytes());
//        // Base64 인코딩된 키를 이용하여 SecretKey 객체를 만든다.
//        SecretKey secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
//
//        Assertions.assertThat(secretKey).isNotNull();
//        log.info("secretKey = {}", secretKey);
//        log.info("secretKeyPlan = {}", secretKeyPlan);
//
//        Assertions.assertThat(secretKeyPlan).isEqualTo(jwtProperties.getSecretkey());
//
//
//    }
//
//}
