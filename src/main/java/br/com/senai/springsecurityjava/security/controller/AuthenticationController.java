package br.com.senai.springsecurityjava.security.controller;

import br.com.senai.springsecurityjava.model.entity.User;
import br.com.senai.springsecurityjava.security.model.Login;
import br.com.senai.springsecurityjava.security.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody Login login,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
//        System.out.print("login");
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
//        o getPrincipal é o usuário a ser autenticado que vai criar o userDetails
//        System.out.println(token.getPrincipal());
        Authentication authentication = authenticationManager.authenticate(token);

        if(authentication.isAuthenticated()){
//            System.out.println(authentication.isAuthenticated());
            User user = (User) authentication.getPrincipal();
            Cookie cookie = CookieUtil.generateCookie(user);
            response.addCookie(cookie);
//        É uma forma de armazenar o usuário autenticado, ele cria o cookie JSESSIONID
//            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//            securityContext.setAuthentication(authentication);
//            SecurityContextHolder.setContext(securityContext);
//            securityContextRepository.saveContext(securityContext, request, response);
//         SecurityContextHolder.getContext().setAuthentication(token);
            return ResponseEntity.ok(authentication.getPrincipal());
        }

        return ResponseEntity.status(401).build();
    }

}
