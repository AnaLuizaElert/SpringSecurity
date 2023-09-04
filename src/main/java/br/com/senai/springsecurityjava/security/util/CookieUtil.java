package br.com.senai.springsecurityjava.security.util;

import br.com.senai.springsecurityjava.model.entity.User;
import br.com.senai.springsecurityjava.security.CookieNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.WebUtils;

public class CookieUtil {

    public static Cookie generateCookie(User user) {
        String token = JWTUtil.generateToken(user);
        Cookie cookie = new Cookie("JWT", token);
        /*Caminho em que é permitido o uso do JWT, ao colocar o barra faz com que seja permitido em tudo*/
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        return cookie;
    }

    public static String getToken(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "JWT");
        if (cookie != null) {
            return cookie.getValue();
        }
        throw new CookieNotFoundException("JWT");
    }
}
