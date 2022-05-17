package com.example.demo.controller;

import com.example.demo.repository.CustomerRepository;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.web.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    CustomerRepository userRepository;

    @PostMapping("/singin")
    public ResponseEntity signIn(@RequestBody AuthRequest request){
        try{
            String name = request.getUserName();
            String token = jwtProvider.createToken(
                    name,
                    userRepository.findUserByusername(name)
                            .orElseThrow(()-> new UsernameNotFoundException("User not found")).getRoles()

            );

            Map<Object, Object> model = new HashMap<>();
            model.put("userName", name);
            model.put("token", token);
            return ResponseEntity.ok(model);

        } catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
