package com.projeto.pet.service;

import com.projeto.pet.entity.EntityCadastroPet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetaillmpl implements UserDetails {

    private Long id;
    private String email;
    private String username;
    private String password;

    private Collection<?extends GrantedAuthority> authorities;

    public UserDetaillmpl(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetaillmpl build(EntityCadastroPet usuario){
        return new UserDetaillmpl(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getPassword(),
                new ArrayList<>());
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
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
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
