package com.example.demo;

import com.example.demo.entity.Role;
import com.example.demo.entity.Sales;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Warehouse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SalesRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class TestDataInit implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

        //userRepository.save(new Customer("user", passwordEncoder.encode("4512"), Collections.singleton("ROLE_USER")));
//        userRepository.save(new Customer("user", passwordEncoder.encode("4512"),
//                Collections.<Role>singleton(new Role("ROLE_USER"))));

    }
}
