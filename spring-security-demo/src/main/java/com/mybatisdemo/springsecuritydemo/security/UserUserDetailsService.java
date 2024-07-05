package com.mybatisdemo.springsecuritydemo.security;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class UserUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User();
    }
}

class MyGrantedAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "system:user:test";
    }
}
class User implements UserDetails {

    //返回授权的所有列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<MyGrantedAuthority> objects = new HashSet<>();
        objects.add(new MyGrantedAuthority());
        return objects;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
