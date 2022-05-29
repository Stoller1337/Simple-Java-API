package com.example.demo.controller;

import com.example.demo.entity.Charges;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.service.CustomerDetailsService;
import com.example.demo.web.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
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

    @Autowired
    CustomerDetailsService customerDetailsService;

    @GetMapping("/all")
    public ModelAndView helloUser(){
        ModelAndView model = new ModelAndView();
        List<Customer> list = customerDetailsService.findAllCustomers();
        model.addObject("user", list).setViewName("Users");
        return model;
    }

    @PostMapping(value = "/login"/*, consumes = "application/json", produces = "application/json"*/)
    public Customer signIn(@RequestBody Customer customer){
        return customerDetailsService.createCustomer(customer);
//        try{
//            String name = request.getUserName();
//            String password = request.getPassword().;
//            //customerDetailsService.createCustomer(request);
//            String token = jwtProvider.createToken(
//                    name,
//                    userRepository.findUserByusername(name)
//                            .orElseThrow(()-> new UsernameNotFoundException("User not found")).getRoles()
//
//            );
//
//            Map<Object, Object> model = new HashMap<>();
//            model.put("userName", name);
//            model.put("token", token);
            //return ResponseEntity.ok(model);

//        } catch (AuthenticationException e){
//            throw new BadCredentialsException("Invalid username or password");
//        }
//        }
    }

}
