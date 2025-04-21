package com.Expense_Management_System.Controller;

import com.Expense_Management_System.Payload.ExpenseDto;
import com.Expense_Management_System.Service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @PostMapping("/addExpense")
    public ResponseEntity<?> addExpense(@RequestBody ExpenseDto expenseDto){
        ExpenseDto expenseDto1 = expenseService.addExpense(expenseDto);
        return new ResponseEntity<>(expenseDto1, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteExpense(@RequestParam long id){
        expenseService.deleteExpense(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<?> updateExpense(@RequestParam long id, @RequestBody ExpenseDto expenseDto){
        ExpenseDto expenseDto1 = expenseService.updateExpense(id, expenseDto);
        return new ResponseEntity<>(expenseDto1,HttpStatus.OK);
    }
}
