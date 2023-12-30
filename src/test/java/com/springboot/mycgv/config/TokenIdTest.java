//package com.springboot.mycgv.config;
//
//import com.springboot.mycgv.config.jwt.JwtProperties;
//import com.springboot.mycgv.config.jwt.TokenProvider;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Encoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import javax.crypto.SecretKey;
//import java.util.Base64;
//import java.util.Date;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@Slf4j
//class TokenIdTest {
//
//    @Autowired
//    private TokenProvider tokenProvider;
//
//    @Autowired
//    private JwtProperties jwtProperties;
//
//    @DisplayName("토큰 생성 테스트")
//    @Test
//    public void createToken() {
////        String testToken = tokenProvider.generateToken("test", (1 * 1000 * 60));
////
////        SecretKey encodedSecretKey = Keys.hmacShaKeyFor(
////                Base64.getEncoder().encodeToString(
////                        jwtProperties.getSecretkey().getBytes()
////                ).getBytes()
////        );
//
//        String Base64SecretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretkey().getBytes());
//        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        SecretKey mysecretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretkey().getBytes());
//        SecretKey Base64hmacShaKey = Keys.hmacShaKeyFor(Base64SecretKey.getBytes());
//
//
//        SecretKey whatisthis = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String getByteKey = Encoders.BASE64.encode(whatisthis.getEncoded());
//
//
////        log.info("testToken = {}", testToken);
//        log.info("secretKey = {}", jwtProperties.getSecretkey());
//        log.info("getBytes() = {}", jwtProperties.getSecretkey().getBytes());
//        log.info("-----------------------------------------------------------");
//        log.info("Base64SecretKey = {}", Base64SecretKey);
//        log.info("Base64SecretKey getBytes() = {}", Base64SecretKey.getBytes());
//        log.info("-----------------------------------------------------------");
//        log.info("secretKeyFor() = {}", secretKey);
//        log.info("-----------------------------------------------------------");
//        log.info("mysecretKey() = {}", mysecretKey);
//        log.info("-----------------------------------------------------------");
//        log.info("Base64hmacShaKey() = {}", Base64hmacShaKey);
//        log.info("-----------------------------------------------------------");
//        log.info("-----------------------------------------------------------");
//        log.info("-----------------------------------------------------------");
//        log.info("secretKeyFor 인코딩 = {}", getByteKey);
//
//
//
//    }
//
//    @DisplayName("토큰 ID 테스트")
//    @Test
//    public void checkIdWithToken() {
//        String testToken = tokenProvider.generateToken("123", (1 * 1000 * 60));
//        String testId = tokenProvider.getId(testToken);
//
//        String testEmail = tokenProvider.getEmail(testToken);
//        Date now = new Date();
//
//        Date expiration = tokenProvider.getClaims(testToken).getExpiration();
//
//        Long remainTime = expiration.getTime() - now.getTime();
//
//        log.info("testId = {}", testId);
//        log.info("testEmail = {}", testEmail);
//        log.info("remaining Time = {}ms", remainTime);
//
//    }
//
//}
