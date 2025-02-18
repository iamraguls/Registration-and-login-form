package com.project.jwtDemoApplication.controller;

import com.project.jwtDemoApplication.entity.Customer;
import com.project.jwtDemoApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String registerCustomer(@RequestBody Customer customer){

        boolean status = customerService.saveCustomer(customer);

        if(status){
            return "Registration Success "+customer.getName();
        }else{
            return "Registration failed";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Customer customer){
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(customer.getEmail(),customer.getPassword());
        Authentication authentication = authenticationManager.authenticate(token); // to pass token
        boolean status = authentication.isAuthenticated();
        if(status){
            return "welcome !!!";
        }else{
            return "login failed, try again";
        }
    }

}
