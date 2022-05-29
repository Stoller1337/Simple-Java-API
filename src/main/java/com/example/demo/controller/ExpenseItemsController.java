package com.example.demo.controller;

import com.example.demo.entity.ExpenseItems;
import com.example.demo.exception.ChargesNotFoundExceptions;
import com.example.demo.service.ExpenseItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("api/expense_items")
public class ExpenseItemsController {
    private ExpenseItemsService expenseItemsService;
    @Autowired
    public void setExpenseItemsService(ExpenseItemsService expenseItemsService) { this.expenseItemsService = expenseItemsService; }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ExpenseItems addExpenseItems(ExpenseItems newExpenseItems){
        return expenseItemsService.addExpenseItems(newExpenseItems);
    }

    @DeleteMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public void deleteExpenseItems(@RequestParam("id") long id){
        try{
            expenseItemsService.deleteExpenseItems(id);
        }catch (ChargesNotFoundExceptions exceptions){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Expense items not found");
        }
    }

    @GetMapping("/all")
    public ModelAndView getAllExpenseItems(){
        ModelAndView model = new ModelAndView();
        List<ExpenseItems> list = expenseItemsService.listExpenseItems();
        model.addObject("expense_items", list);
        model.setViewName("ExpenseItems");
        return model;
    }

    @GetMapping("/id")
    public ModelAndView getExpenseItems(@RequestParam("id") long id){
        ModelAndView model = new ModelAndView();
        model.addObject("expense_items", expenseItemsService.findById(id));
        model.setViewName("ExpenseItems");
        return model;
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

    @PostMapping("/update/name")
    public void updateExpenseItemsName(@RequestParam("id") long id,@RequestParam("name") String name){
        try{
            expenseItemsService.updateName(id, name);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expense items not found");
        }
    }
}
