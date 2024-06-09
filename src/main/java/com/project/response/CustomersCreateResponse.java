package com.project.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomersCreateResponse {

    private String code;

    private String name;

    private String cpfCnpj;

    private String cellphone;

    private String email;

}
