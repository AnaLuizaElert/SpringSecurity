package br.com.senai.springsecurityjava.controller;

import br.com.senai.springsecurityjava.model.entity.User;
import br.com.senai.springsecurityjava.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<String> get(){
        return ResponseEntity.ok("Hello World! não autenticado");
    }

    @GetMapping("/auth")
    public ResponseEntity<String> get2(){
        return ResponseEntity.ok("Hello World! autenticado");
    }
    @GetMapping("/auth/admin")
    public ResponseEntity<String> get3(){
        return ResponseEntity.ok("Hello World! admin");
    }

    @GetMapping("/auth/seller")
    public ResponseEntity<String> get4(){
        return ResponseEntity.ok("Hello World! seller");
    }

    @GetMapping("/auth/client")
    public ResponseEntity<String> get5(){
        return ResponseEntity.ok("Hello World! client");
    }

    @GetMapping("/auth/adminSeller")
    public ResponseEntity<String> get6(){
        return ResponseEntity.ok("Hello World! admin-seller");
    }

    @PostMapping
    public ResponseEntity<User> post(@RequestBody User user){
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        user.setPassword(bcp.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }
}