package br.com.senai.springsecurityjava.security.util;

import br.com.senai.springsecurityjava.model.entity.Person;
import br.com.senai.springsecurityjava.security.CookieNotFound;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.WebUtils;

public class CookieUtil {

    public static Cookie generateCookie(Person person) {
        String token = JWTUtil.generateToken(person);
        Cookie cookie = new Cookie("JWT", token);
        cookie.setPath("/");
        cookie.setMaxAge(1800);
        return cookie;
    }

    public static String getToken(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "JWT");
        if(cookie != null){
            return cookie.getValue();
        }
        throw new CookieNotFound();
    }
}
