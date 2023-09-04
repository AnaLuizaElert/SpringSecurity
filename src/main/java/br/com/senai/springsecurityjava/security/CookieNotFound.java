package br.com.senai.springsecurityjava.security;

public class CookieNotFound extends RuntimeException{
    public CookieNotFound(){
        super("Cookie não encontrado!");
    }
}
