package br.com.senai.springsecurityjava.security.util;

import br.com.senai.springsecurityjava.model.entity.User;
import jakarta.servlet.http.Cookie;

public class CookieUtil {

    public static Cookie generateCookie(User user) {
        String token = JWTUtil.generateToken(user);
        Cookie cookie = new Cookie("JWT", token);
        /*Caminho em que é permitido o uso do JWT, ao colocar o barra faz com que seja permitido em tudo*/
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        return cookie;
    }
}
