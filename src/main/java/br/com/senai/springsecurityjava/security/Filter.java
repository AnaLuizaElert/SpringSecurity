package br.com.senai.springsecurityjava.security;

import br.com.senai.springsecurityjava.security.model.User;
import br.com.senai.springsecurityjava.security.util.CookieUtil;
import br.com.senai.springsecurityjava.security.util.JWTUtil;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class Filter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        if(privatePath(request.getRequestURI())){

            try{
                String token = CookieUtil.getToken(request);
                User user = JWTUtil.getUsuario(token);
                response.addCookie(CookieUtil.generateCookie(user));
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(), null, user.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (JWTDecodeException e){
                System.out.println("O token não foi encontrado!");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            } catch (CookieNotFound e){
                System.out.println(e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /*O correto é definir sempre as rotas públicas, não as privadas*/
    private boolean privatePath(String url){
        return url.startsWith("/teste/autenticado");
    }
}
