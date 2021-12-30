package com.desafio.exactaworks.mapper;

import com.desafio.exactaworks.model.Expenses;
import com.desafio.exactaworks.model.dto.CreateExpensesDTO;
import com.desafio.exactaworks.model.dto.GetExpensesDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpensesMapper {

    @Autowired
    protected ModelMapper mapper;

    public Expenses createToEntity(CreateExpensesDTO dto) {
        return mapper.map(dto, Expenses.class);
    }

    public Expenses getToEntity(GetExpensesDTO dto) {
        return mapper.map(dto, Expenses.class);
    }

    public GetExpensesDTO toDTO(Expenses entity) {
        return mapper.map(entity, GetExpensesDTO.class);
    }

    public List<GetExpensesDTO> toDTOList(List<Expenses> list) {
        return list.stream().map(this::toDTO).collect(Collectors.toList());
    }

}
