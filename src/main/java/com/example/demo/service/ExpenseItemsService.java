package com.example.demo.service;

import com.example.demo.entity.ExpenseItems;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExpenseItemsService {

    List<ExpenseItems> listExpenseItems();//y

    @Transactional
    ExpenseItems addExpenseItems(ExpenseItems expenseItems);//y

    void deleteExpenseItems(long id);//y

    List<ExpenseItems> sortASCById();//y
    List<ExpenseItems> sortDESCById();//y
    ExpenseItems findById(long id);//y
    List<ExpenseItems> updateName(long id, String name);//y

}
