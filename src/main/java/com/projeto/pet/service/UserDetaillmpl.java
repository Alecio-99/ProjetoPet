package com.projeto.pet.service;

import com.projeto.pet.entity.EntityCadastroPet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetaillmpl implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetaillmpl(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetaillmpl build(EntityCadastroPet usuario) {
        return new UserDetaillmpl(
                usuario.getId(),
                usuario.getEmail(),  // Usa email corretamente
                usuario.getPassword(),
                Collections.emptyList() // Pode adicionar roles depois
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password; // Retorna a senha correta
    }

    @Override
    public String getUsername() {
        return email; // Retorna email como identificador
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
