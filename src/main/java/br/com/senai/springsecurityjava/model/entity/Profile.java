package br.com.senai.springsecurityjava.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Profile implements GrantedAuthority {
    ADMIN, SELLER, CLIENT;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
