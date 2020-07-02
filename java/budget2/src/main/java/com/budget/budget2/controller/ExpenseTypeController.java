package com.budget.budget2.controller;

import com.budget.budget2.dto.ExpenseTypeDTO;
import com.budget.budget2.entity.ExpenseType;
import com.budget.budget2.helpers.ResponseHelper;
import com.budget.budget2.mapper.ExpenseTypeMapper;
import com.budget.budget2.repository.ExpenseTypeRepository;
import com.budget.budget2.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/expense-type")
public class ExpenseTypeController {

    private final ExpenseTypeRepository expenseTypeRepository;
    private final ExpenseTypeMapper expenseTypeMapper;
    private final ResponseHelper responseHelper;

    @Autowired
    public ExpenseTypeController(ExpenseTypeRepository expenseTypeRepository, ExpenseTypeMapper expenseTypeMapper, ResponseHelper responseHelper) {
        this.expenseTypeRepository = expenseTypeRepository;
        this.expenseTypeMapper = expenseTypeMapper;
        this.responseHelper = responseHelper;
    }

    @GetMapping()
    public ResponseEntity<Response> getAll() {
        List<ExpenseType> expenseTypes = expenseTypeRepository.findAll();
        Response<List<ExpenseTypeDTO>> response = new Response<>();

        if (expenseTypes.size() == 0) {
            response.setErrorMessage("not found expenses");
            return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
        }

        response.setContent(expenseTypeMapper.fromExpenseTypeListToExpenseDTOList(expenseTypes));

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getOne(@PathVariable int id) {
        ExpenseType expenseType = expenseTypeRepository.getOne(id);
        Response<ExpenseTypeDTO> response = new Response<>();

        if (expenseType == null) {
            response.setErrorMessage("not found " + id);
            return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
        }

        response.setContent(expenseTypeMapper.fromExpenseTypeToExpenseDTO(expenseType));

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response> createExpenseType(@RequestBody @Valid ExpenseTypeDTO expenseTypeDTO, BindingResult bindingResult) {
        Response<Object> response = new Response<>();
        if (bindingResult.hasErrors()) {
            response = responseHelper.addErrors(bindingResult, response);
            response.setContent(expenseTypeDTO);
            return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
        }

        ExpenseType expenseType = expenseTypeMapper.fromExpenseDTOToExpense(expenseTypeDTO);
        ExpenseType newExpenseType = expenseTypeRepository.save(expenseType);
        expenseTypeMapper.fromExpenseTypeToExpenseDTO(newExpenseType);
        response.setContent(newExpenseType);

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
}
