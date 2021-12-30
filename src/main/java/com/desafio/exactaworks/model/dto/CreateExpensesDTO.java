package com.desafio.exactaworks.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateExpensesDTO {

    @NotBlank
    private String peopleName;

    private String description;

    @NotNull
    @PositiveOrZero
    private BigDecimal amount;

    private List<String> tags;
}
