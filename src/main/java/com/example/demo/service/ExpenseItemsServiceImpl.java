package com.example.demo.service;

import com.example.demo.entity.ExpenseItems;
import com.example.demo.exception.ChargesNotFoundExceptions;
import com.example.demo.repository.ExpenseItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseItemsServiceImpl implements ExpenseItemsService{

    @Autowired
    private ExpenseItemsRepository expenseItemsRepository;

    @Override
    public List<ExpenseItems> listExpenseItems() {
        return(List<ExpenseItems>) expenseItemsRepository.findAll();
    }

    @Override
    public ExpenseItems findById(long id) {
        Optional<ExpenseItems> optionalExpenseItems = expenseItemsRepository.findById(id);
        if(optionalExpenseItems.isPresent()){
            return optionalExpenseItems.get();
        }else{
            throw new ChargesNotFoundExceptions("Expense items not found");
        }
    }

    @Transactional
    @Override
    public ExpenseItems addExpenseItems(ExpenseItems expenseItems) {
        return expenseItemsRepository.save(expenseItems);
    }

    @Override
    public void deleteExpenseItems(long id) {
        Optional<ExpenseItems> optionalExpenseItems = expenseItemsRepository.findById(id);
        if(optionalExpenseItems.isPresent()){
            expenseItemsRepository.deleteById(id);
        }else{
            throw new ChargesNotFoundExceptions("Expense items not found");
        }
    }

    @Override
    public List<ExpenseItems> sortASCById() {
        return expenseItemsRepository.sortASCById();
    }

    @Override
    public List<ExpenseItems> sortDESCById() {
        return expenseItemsRepository.sortDESCById();
    }

    @Override
    public List<ExpenseItems> updateName(long id, String name) {
        Optional<ExpenseItems> optionalExpenseItems = expenseItemsRepository.findById(id);
        if(optionalExpenseItems.isPresent()){
            return expenseItemsRepository.updateName(id, name);
        }else{
            throw new ChargesNotFoundExceptions("Expense items not found");
        }
    }

}
