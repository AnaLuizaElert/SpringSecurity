package br.com.senai.springsecurityjava.controller;

import br.com.senai.springsecurityjava.model.entity.User;
import br.com.senai.springsecurityjava.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teste")
public class TestController {

    private UserRepository userRepository;

    /*TODO: se descomentar de settings não precisa desse PreAuthorized. São duas opções, lá ou aqui*/
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

    @PostMapping
    public ResponseEntity<User> post(
            @RequestBody User user
    ){
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        user.setPassword(bcp.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

}
