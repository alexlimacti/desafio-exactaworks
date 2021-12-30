package com.desafio.exactaworks.controller;

import com.desafio.exactaworks.model.dto.CreateExpensesDTO;
import com.desafio.exactaworks.model.dto.GetExpensesDTO;
import com.desafio.exactaworks.service.ExpensesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "Expenses")
@RequestMapping(value = "/expenses")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @ApiOperation(value = "Get Expenses List")
    @GetMapping
    private ResponseEntity<Page<GetExpensesDTO>> getList(Pageable pageable) {
        return ResponseEntity.ok().body(expensesService.list(pageable));
    }

    @ApiOperation(value = "Create Expenses")
    @PostMapping
    private ResponseEntity<GetExpensesDTO> createExpenses(@RequestBody @Valid CreateExpensesDTO dto){
        return ResponseEntity.ok(expensesService.createExpenses(dto));
    }

    @ApiOperation(value = "Get Expenses by Id ")
    @GetMapping("/{expensesId}")
    private ResponseEntity<GetExpensesDTO> getExpenses(@PathVariable Long expensesId) {
        return ResponseEntity.ok(expensesService.findById(expensesId));
    }

    @ApiOperation(value = "Update Expenses")
    @PutMapping("/{expensesId}")
    private ResponseEntity<GetExpensesDTO> updateExpenses(@PathVariable Long expensesId, @RequestBody @Valid CreateExpensesDTO dto) {
        return ResponseEntity.ok(expensesService.updateExpenses(expensesId, dto));
    }

    @ApiOperation(value = "Delete Expenses by Id ")
    @DeleteMapping("/{expensesId}")
    private ResponseEntity<?> deleteExpenses(@PathVariable Long expensesId) {
        expensesService.delete(expensesId);
        return ResponseEntity.noContent().build();
    }

}
