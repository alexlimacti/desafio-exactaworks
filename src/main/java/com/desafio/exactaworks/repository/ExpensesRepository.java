package com.desafio.exactaworks.repository;

import com.desafio.exactaworks.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

}
