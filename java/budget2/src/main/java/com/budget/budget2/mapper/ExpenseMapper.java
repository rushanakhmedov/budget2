package com.budget.budget2.mapper;

import com.budget.budget2.dto.ExpenseDTO;
import com.budget.budget2.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    @Mapping(source = "created", target = "createdAt", dateFormat = "d.m.Y")
    @Mapping(source = "expenseType", target = "expenseType")
    ExpenseDTO fromExpenseToExpenseDTO(Expense expense);

    List<ExpenseDTO> fromListExpenseToListExpenseDTO(List<Expense> expense);

    Expense fromExpenseDTOToExpense(ExpenseDTO expenseDTO);
}
