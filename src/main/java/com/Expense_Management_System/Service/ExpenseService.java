package com.Expense_Management_System.Service;

import com.Expense_Management_System.Entity.Expense;
import com.Expense_Management_System.Payload.ExpenseDto;
import com.Expense_Management_System.Repository.ExpenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private ModelMapper modelMapper;

    public ExpenseService(ExpenseRepository expenseRepository, ModelMapper modelMapper) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
    }
    ExpenseDto MapToDto (Expense expense){
        return modelMapper.map(expense,ExpenseDto.class);
    }
    Expense MapToEntity (ExpenseDto expenseDto){
        return modelMapper.map(expenseDto,Expense.class);
    }

    public ExpenseDto addExpense(ExpenseDto expenseDto) {
        Expense expense = MapToEntity(expenseDto);
        Expense expense1 = expenseRepository.save(expense);
        return MapToDto(expense1);
    }

    public void deleteExpense(long id) {
        expenseRepository.deleteById(id);
    }

    public ExpenseDto updateExpense(long id, ExpenseDto expenseDto) {
        Expense expense = MapToEntity(expenseDto);
        expense.setId(id);
        Expense save = expenseRepository.save(expense);
        return MapToDto(save);

    }
}
