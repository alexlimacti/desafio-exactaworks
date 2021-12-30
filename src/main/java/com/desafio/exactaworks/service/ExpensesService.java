package com.desafio.exactaworks.service;

import com.desafio.exactaworks.mapper.ExpensesMapper;
import com.desafio.exactaworks.exception.ExpensesNotFoundException;
import com.desafio.exactaworks.model.Expenses;
import com.desafio.exactaworks.model.dto.CreateExpensesDTO;
import com.desafio.exactaworks.model.dto.GetExpensesDTO;
import com.desafio.exactaworks.repository.ExpensesRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private ExpensesMapper mapper;

    private static final String EXPENSES_DONT_FOUND = "Lançamento não encontrado";

    public GetExpensesDTO createExpenses(CreateExpensesDTO dto) {
        var entity = mapper.createToEntity(dto);
        entity.setHourTime(LocalDateTime.now());
        return mapper.toDTO(expensesRepository.save(entity));
    }

    @Transactional(readOnly = true)
    public Page<GetExpensesDTO> list(Pageable pageable) {
        Page<Expenses> page = expensesRepository.findAll(pageable);
        return new PageImpl<>(mapper.toDTOList(page.getContent()), page.getPageable(), page.getTotalElements());
    }

    public GetExpensesDTO findById(Long id) {
        var entity = expensesRepository.findById(id).orElseThrow(() -> new ExpensesNotFoundException(EXPENSES_DONT_FOUND));
        return mapper.toDTO(entity);
    }

    public void delete(Long id) {
        var entity = mapper.getToEntity(findById(id));
        expensesRepository.delete(entity);
    }

    public GetExpensesDTO updateExpenses(Long id, CreateExpensesDTO dto) {
        try {
            var entity = mapper.getToEntity(findById(id));
            BeanUtils.copyProperties(dto, entity,
                    "id");
            return mapper.toDTO(expensesRepository.save(entity));
        } catch (Exception e) {
            throw new ExpensesNotFoundException(EXPENSES_DONT_FOUND);
        }
    }

    private void merge(Map<String, Object> dadosOrigem, Expenses destinationExpenses, HttpServletRequest request) {
        ServletServerHttpRequest servletHttpRequest = new ServletServerHttpRequest(request);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            Expenses originExpenses = objectMapper.convertValue(dadosOrigem, Expenses.class);
            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Expenses.class, nomePropriedade);
                field.setAccessible(true);
                Object novoValor = ReflectionUtils.getField(field, originExpenses);
                ReflectionUtils.setField(field, destinationExpenses, novoValor);
            });
        } catch (IllegalArgumentException e) {
            //Throwable rootCause = ExceptionUtils.getRootCause(e);
            //throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletHttpRequest);
        }
    }

}
