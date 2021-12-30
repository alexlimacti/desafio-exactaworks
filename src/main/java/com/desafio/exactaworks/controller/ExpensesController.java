package com.desafio.exactaworks.controller;

import com.desafio.exactaworks.model.dto.CreateExpensesDTO;
import com.desafio.exactaworks.model.dto.GetExpensesDTO;
import com.desafio.exactaworks.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/expenses")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @GetMapping
    private ResponseEntity<Page<GetExpensesDTO>> getList(Pageable pageable) {
        return ResponseEntity.ok().body(expensesService.list(pageable));
    }

    @PostMapping
    private ResponseEntity<GetExpensesDTO> createExpenses(@RequestBody @Valid CreateExpensesDTO dto){
        return ResponseEntity.ok(expensesService.createExpenses(dto));
    }

}
