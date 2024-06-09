package com.project.repository;

import com.project.model.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, BigInteger> {

    Boolean existsByCode(String code);

    Customers findByCode(String code);

    Page<Customers> findAllByOrderById(Pageable pageable);

    Page<Customers> findByNameContainingOrderByName(String name, Pageable pageable);

}
