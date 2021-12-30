package com.desafio.exactaworks.domain.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ExpensesDTO {
    private Long id;
    private String peopleName;
    private String description;
    private LocalDateTime hourTime;
    private Double value;
    private List<String> tags;
}
