package com.project.jwtDemoApplication.service;

import com.project.jwtDemoApplication.entity.Customer;
import com.project.jwtDemoApplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Customer customer = customerRepository.findByEmail(email);
        return new User(customer.getEmail(),customer.getPassword(), Collections.emptyList());
    }

    public boolean saveCustomer(Customer customer){

        String encodedPassword = bcrypt.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customerRepository.save(customer);
        return customer.getId()!=null;
    }


}
