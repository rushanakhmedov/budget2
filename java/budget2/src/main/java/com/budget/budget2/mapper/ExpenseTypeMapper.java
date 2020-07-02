package com.budget.budget2.mapper;

import com.budget.budget2.dto.ExpenseTypeDTO;
import com.budget.budget2.entity.ExpenseType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseTypeMapper {
    ExpenseTypeDTO fromExpenseTypeToExpenseDTO(ExpenseType expenseType);
    List<ExpenseTypeDTO> fromExpenseTypeListToExpenseDTOList(List<ExpenseType> expenseTypeList);

    ExpenseType fromExpenseDTOToExpense(ExpenseTypeDTO expenseTypeDTO);
}
