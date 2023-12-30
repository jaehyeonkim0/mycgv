package com.springboot.mycgv.config.jwt;

import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {

    private final JwtProperties jwtProperties;
    private final MemberRepository memberRepository;
//    String encodedKey =
//            Base64.getEncoder().encodeToString(jwtProperties.getSecretkey().getBytes());

    public String generateToken(String id, long expTime) {

        if(expTime<=0) {
            throw new RuntimeException("만료시간이 0보다 작습니다");
        }else if(!memberRepository.existsById(id)){
            throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + id);
        }

        Date now = new Date();
        return makeToken(memberRepository.findOneById(id), expTime);
    }

    private String makeToken(Member member, long expTime) {

        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .setSubject(member.getEmail())
                .claim("id", member.getId())
                .signWith(Keys.hmacShaKeyFor(Base64.getEncoder().encodeToString(jwtProperties.getSecretkey().getBytes()).getBytes()), SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean validToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(Base64.getEncoder().encodeToString(jwtProperties.getSecretkey().getBytes()).getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public String getId(String token) {

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Base64.getEncoder().encodeToString(jwtProperties.getSecretkey().getBytes()).getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("id", String.class);
    }

    public String getEmail(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Base64.getEncoder().encodeToString(jwtProperties.getSecretkey().getBytes()).getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Claims getClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(
                        Keys.hmacShaKeyFor(
                                Base64.getEncoder().encodeToString(
                                        jwtProperties.getSecretkey().getBytes()).getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
//        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(claims.get("id", String.class), "");
    }


//    public Authentication getAuthentication(String token) {
//        Claims claims = getClaims(token);
//
//        return new UsernamePasswordAuthenticationToken(new CustomUserDetails(claims.getId(), ""), token);
//    }
//
//    private Claims getClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(Keys.hmacShaKeyFor(encodedKey.getBytes()))
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//    }
}
