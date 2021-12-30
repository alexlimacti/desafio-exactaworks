package com.desafio.exactaworks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpensesNotFoundException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExpensesNotFoundException(String message) {
        super(message);
    }

    public ExpensesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
