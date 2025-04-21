package com.Expense_Management_System.Repository;

import com.Expense_Management_System.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}