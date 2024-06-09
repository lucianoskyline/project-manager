package com.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "customers")
@Data
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    private String name;

    private String cpfCnpj;

    private String cellphone;

    private String email;

    private String code;

}
