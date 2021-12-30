package com.desafio.exactaworks.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "tb_expenses")
public class Expenses implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String peopleName;
    private String description;
    private LocalDateTime hourTime;
    private BigDecimal amount;

    @ElementCollection
    @CollectionTable(name = "list_tags", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "tags")
    private List<String> tags;

}
