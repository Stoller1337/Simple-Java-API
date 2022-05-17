package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Role;
import com.example.demo.exception.InvalidJwtAuthenticationException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RoleRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    CustomerRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByusername(username)
                .orElseThrow(()-> new UsernameNotFoundException("UserName not found"));
    }

    public Customer createCustomer(Customer customer){
        try {
            Customer newCustomer = new Customer(customer);
            System.out.println("PASSWORD ------------------------------------------------->" + customer.getPassword());
            newCustomer.setPassword(bCryptPasswordEncoder.encode(newCustomer.getPassword()));
            newCustomer.setRoles(Collections.singleton(roleRepository.getById(1L)));
            return userRepository.save(newCustomer);
        } catch (AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    public void deleteCustomer(Long id){
        Optional<Customer> optionalCustomer = userRepository.findById(id);
        if(optionalCustomer.isPresent()){
            userRepository.deleteById(id);
        } else{
            throw new UserNotFoundException("User not found");
        }
    }

    public Customer findCustomerById(Long id){
        Optional<Customer> optionalCustomer = userRepository.findById(id);
        if(optionalCustomer.isPresent()){
            return optionalCustomer.get();
        } else{
            throw new UserNotFoundException("User not found ");
        }
    }

    public List<Customer> findAllCustomers(){
        return userRepository.findAll();
    }
}
