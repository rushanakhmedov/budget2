package com.budget.budget2.controller;

import com.budget.budget2.dto.ExpenseDTO;
import com.budget.budget2.entity.Expense;
import com.budget.budget2.mapper.ExpenseMapper;
import com.budget.budget2.repository.ExpenseRepository;
import com.budget.budget2.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    @Autowired
    public ExpenseController(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }

    //@PreAuthorize( "hasAuthority('USER')" )
    @PreAuthorize("hasRole(@roles.ADMIN)")
    @GetMapping()
    public ResponseEntity<Response> getAll(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "5") int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(offset, limit);
        //List<Expense> expenses = expenseRepository.findAll(pageable);
        Page<Expense> expensesPage = expenseRepository.findAll(pageable);
        List<Expense> expenses = expensesPage.getContent();

        Response<List<ExpenseDTO>> response = new Response<>();

        if (expenses.size() == 0) {
            response.setErrorMessage("not found expenses");
            return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
        }

        response.setContent(expenseMapper.fromListExpenseToListExpenseDTO(expenses));

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Response<ExpenseDTO> getOne(@PathVariable long id) {
        Expense expense = expenseRepository.getOne(id);
        ExpenseDTO expenseDTO = expenseMapper.fromExpenseToExpenseDTO(expense);

        return new Response<ExpenseDTO>(expenseDTO);
    }

    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        Expense expenseSaved = expenseRepository.save(expense);

        return expenseSaved;
    }
}
