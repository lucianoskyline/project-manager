package com.project.service;

import com.project.model.Customers;
import com.project.repository.CustomersRepository;
import com.project.request.CustomersCreateRequest;
import com.project.response.CustomersCreateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public CustomersCreateResponse create(CustomersCreateRequest request) {
        var customer = modelMapper.map(request, Customers.class);
        customer.setRegistrationDate(new Date());
        customer.setCode(UUID.randomUUID().toString());
        customer = customersRepository.save(customer);
        return convertToResponse(customer);
    }

    private CustomersCreateResponse convertToResponse(Customers customer) {
        return modelMapper.map(customer, CustomersCreateResponse.class);
    }

    public CustomersCreateResponse update(String code, CustomersCreateRequest request) {
        var customer = customersRepository.findByCode(code);
        if (customer != null) {
            customer.setName(request.getName());
            customer.setCellphone(request.getCellphone());
            customer.setEmail(request.getEmail());

            customer = customersRepository.save(customer);
            return convertToResponse(customer);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public void delete(String code) {
        var customer = customersRepository.findByCode(code);
        if (customer != null) {
            customersRepository.delete(customer);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public CustomersCreateResponse select(String code) {
        var customer = customersRepository.findByCode(code);
        if (customer != null) {
            return convertToResponse(customer);
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public Page<CustomersCreateResponse> search(String name, Pageable pageable) {
        Page<Customers> customers = null;
        if (name != null && !name.isBlank()) {
            customers = customersRepository.findByNameContainingOrderByName(name, pageable);
        } else {
            customers = customersRepository.findAllByOrderById(pageable);
        }
        return new PageImpl<>(convertListToResponse(customers.getContent()), pageable, customers.getTotalPages());
    }

    private List<CustomersCreateResponse> convertListToResponse(List<Customers> customers) {
        List<CustomersCreateResponse> response = new ArrayList<>();
        customers.forEach(c -> {
            response.add(convertToResponse(c));
        });
        return response;
    }

}
