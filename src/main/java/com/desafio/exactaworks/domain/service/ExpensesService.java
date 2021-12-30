package com.desafio.exactaworks.domain.service;

import com.desafio.exactaworks.domain.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;


}
