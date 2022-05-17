package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerDetailsService customerDetailsService;
    private CustomerRepository customerRepository;

    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public Customer createCustomer(@RequestBody Customer customer){
        System.out.println("PASSWORD IN CONTROLLER ------------------------------------------------->" + customer.getPassword());
        return customerDetailsService.createCustomer(customer);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            return new ResponseEntity<>(customerDetailsService.findAllCustomers(), HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteCustomer(@PathVariable Long id) throws UserNotFoundException{
        customerDetailsService.deleteCustomer(id);
    }
}
