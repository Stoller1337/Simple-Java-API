package com.example.demo.controller;

import com.example.demo.entity.ExpenseItems;
import com.example.demo.exception.ChargesNotFoundExceptions;
import com.example.demo.service.ExpenseItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/expense_items")
public class ExpenseItemsController {
    private ExpenseItemsService expenseItemsService;
    @Autowired
    public void setExpenseItemsService(ExpenseItemsService expenseItemsService) { this.expenseItemsService = expenseItemsService; }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ExpenseItems addExpenseItems(@RequestBody ExpenseItems newExpenseItems){
        return expenseItemsService.addExpenseItems(newExpenseItems);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = "application/json", produces = "application/json")
    public void deleteExpenseItems(@PathVariable("id") long id){
        try{
            expenseItemsService.deleteExpenseItems(id);
        }catch (ChargesNotFoundExceptions exceptions){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Expense items not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExpenseItems>> getAllExpenseItems(){
        List<ExpenseItems> list = expenseItemsService.listExpenseItems();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseItems> getExpenseItems(@PathVariable("id") long id){
        try{
            return new ResponseEntity<>(expenseItemsService.findById(id), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Charges not found");
        }
    }

    @GetMapping("/sort/asc")
    public ResponseEntity<ExpenseItems> sortExpenseItemsASC(){
        try{
            return new ResponseEntity(expenseItemsService.sortASCById(), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense items not found");
        }
    }

    @GetMapping("/sort/desc")
    public ResponseEntity<ExpenseItems> sortExpenseItemsDESC(){
        try{
            return new ResponseEntity(expenseItemsService.sortDESCById(), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense items not found");
        }
    }

    @PostMapping("/update/name/{id}")
    public ResponseEntity<ExpenseItems> updateExpenseItemsName(@PathVariable("id") long id, String name){
        try{
            return new ResponseEntity(expenseItemsService.updateName(id, name), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense items not found");
        }
    }
}
