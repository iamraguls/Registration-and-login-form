package com.project.jwtDemoApplication.service;

import com.project.jwtDemoApplication.entity.Customer;
import com.project.jwtDemoApplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    public boolean saveCustomer(Customer customer){

        String encodedPassword = bcrypt.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);

        return customer.getId()!=null;
    }

}
