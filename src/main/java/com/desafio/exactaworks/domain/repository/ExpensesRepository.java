package com.desafio.exactaworks.domain.repository;

import com.desafio.exactaworks.domain.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

}
