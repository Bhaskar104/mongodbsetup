package com.mongosetup.mongodbsetup.service;

import com.mongosetup.mongodbsetup.model.Expense;
import com.mongosetup.mongodbsetup.reopsitory.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense){
        expenseRepository.insert(expense);

    }
    public void updateExpense(Expense expense) throws Throwable {
        Expense oneResult = (Expense) expenseRepository.findById(expense.getId()).orElseThrow(() -> new RuntimeException(String.format("Cannot find Expense by ID %s", expense.getId())));
        oneResult.setExpenseAmount(expense.getExpenseAmount());
        oneResult.setExpenseName(expense.getExpenseName());
        oneResult.setExpenseCategory(expense.getExpenseCategory());

        expenseRepository.save(expense);
    }
    public List<Expense> getAllExpense(){
        return expenseRepository.findAll();
    }
    public Expense getExpenseByName(String name){
        return expenseRepository.findByName(name).orElseThrow(() ->new RuntimeException(String.format("Can not find Expense by name", name)));

    }


    public void deleteExpense(String id){
        expenseRepository.deleteById(id);

    }
}
