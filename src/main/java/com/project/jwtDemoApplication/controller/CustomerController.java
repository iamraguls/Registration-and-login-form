package com.project.jwtDemoApplication.controller;

import com.project.jwtDemoApplication.entity.Customer;
import com.project.jwtDemoApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public String registerCustomer(@RequestBody Customer customer){

        boolean status = customerService.saveCustomer(customer);

        if(status){
            return "Registration Success "+customer.getName();
        }else{
            return "Registration failed";
        }

    }

}
