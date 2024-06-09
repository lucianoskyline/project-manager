package com.project.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CustomersCreateRequest {

    @NotBlank(message = "Informe o nome")
    private String name;

    @NotBlank(message = "Informe o CPF ou CNPJ")
    private String cpfCnpj;

    private String cellphone;

    private String email;

}
