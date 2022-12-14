package com.codegym.repository.customer;

import com.codegym.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    Page<Customer> findAllByNameContaining(String nameFacility, Pageable pageable);

    Page<Customer> findAllByContracts_EndDateGreaterThan(String now ,Pageable pageable);


}