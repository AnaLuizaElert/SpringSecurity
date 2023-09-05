package br.com.senai.springsecurityjava.controller;

import br.com.senai.springsecurityjava.security.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teste")
public class TestController {

    private UserRepository userRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/autenticado")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Hello World! autenticado");
    }

    @GetMapping("/nautenticado")
    public ResponseEntity<String> get2(){
        return ResponseEntity.ok("Hello World! não autenticado");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/autenticado/admin")
    public ResponseEntity<String> get3(){
        return ResponseEntity.ok("Hello World! admin");
    }

    @PreAuthorize("hasAuthority('SELLER')")
    @GetMapping("/autenticado/seller")
    public ResponseEntity<String> get4(){
        return ResponseEntity.ok("Hello World! seller");
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @GetMapping("/autenticado/client")
    public ResponseEntity<String> get5(){
        return ResponseEntity.ok("Hello World! client");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER')")
    @GetMapping("/autenticado/admin&seller")
    public ResponseEntity<String> get6(){
        return ResponseEntity.ok("Hello World! admin and seller");
    }

}
