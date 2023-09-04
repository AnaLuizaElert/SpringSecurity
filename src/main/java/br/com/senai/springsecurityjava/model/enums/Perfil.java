package br.com.senai.springsecurityjava.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Perfil implements GrantedAuthority {

    ADMIN, SELLER, CLIENT;

    @Override
    public String getAuthority() {
        return this.name();
    }
}