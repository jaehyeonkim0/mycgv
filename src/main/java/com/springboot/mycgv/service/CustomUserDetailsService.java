package com.springboot.mycgv.service;

import com.springboot.mycgv.repository.Membermapper2;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@AllArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final Membermapper2 membermapper2;

    @Override
    public UserDetails loadUserByUsername(String id) {

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        com.springboot.mycgv.model.User user = membermapper2.findOneById(id);

        if (user != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority("USER")); // USER 라는 역할을 넣어준다.
            return new User(user.getId(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("can not find User : " + id);
        }
    }

}
