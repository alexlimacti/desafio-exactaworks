package com.desafio.exactaworks.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GetExpensesDTO {

    private Long id;
    private String peopleName;
    private String description;
    private BigDecimal amount;
    private List<String> tags;

}
