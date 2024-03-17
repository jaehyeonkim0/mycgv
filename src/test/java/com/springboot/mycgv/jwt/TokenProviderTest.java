package com.springboot.mycgv.jwt;

import com.springboot.mycgv.config.jwt.TokenProvider;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Test
    public void generateAccessToken() {
        String token = tokenProvider.generateToken("test1");
        log.info("access Token = {}", token);
    }

}