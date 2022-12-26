package com.tartan;

import org.springframework.data.jpa.repository.JpaRepository;


// This is for CRUD operation on customer database using spring data jpa
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // write custom repository
}
