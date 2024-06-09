package com.project.controller;

import com.project.exception.BusinessMessage;
import com.project.service.CustomersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.request.CustomersCreateRequest;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomersService customersService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody CustomersCreateRequest request) {
        try {
            return ResponseEntity.ok(customersService.create(request));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new BusinessMessage(ex.getMessage()));
        }
    }

    @PutMapping("/{code}")
    public ResponseEntity update(@PathVariable String code, @RequestBody CustomersCreateRequest request) {
        try {
            return ResponseEntity.ok(customersService.update(code, request));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new BusinessMessage(ex.getMessage()));
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity delete(@PathVariable String code) {
        try {
            customersService.delete(code);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new BusinessMessage(ex.getMessage()));
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity select(@PathVariable String code) {
        try {
            return ResponseEntity.ok(customersService.select(code));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new BusinessMessage(ex.getMessage()));
        }
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String name, Pageable pageable) {
        try {
            return ResponseEntity.ok(customersService.search(name, pageable));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new BusinessMessage(ex.getMessage()));
        }
    }

}
