package com.springboot.mycgv.config.jwt;

import com.springboot.mycgv.model.Member;
import com.springboot.mycgv.repository.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Log4j2
public class TokenProvider {
    private final JwtProperties jwtProperties;
    private final MemberRepository memberRepository;

    public String generateToken(String id) {
        Member member = memberRepository.findOneById(id);


        if(!memberRepository.existsById(id)){
            throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + id);
        }
        return makeToken(member);
    }

    private String makeToken(Member member) {
        Date now = new Date();
        long expTime = jwtProperties.getExptime();
        
        Claims claims = Jwts.claims()
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .setSubject(member.getEmail());

        claims.put("id", member.getId());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretkey().getBytes());
    }

    public String getUserId(String token) {
        return getAllClaims(token).get("id", String.class);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUserId(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return getTokenExpired(token).before(new Date());
    }

    private Date getTokenExpired(String token) {
        return getAllClaims(token).getExpiration();
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }






















    //--------------------------------------------------------------------------------------------------------

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


//    public String getUserId(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        return claims.get("id", String.class);
//    }
}
