package com.example.demo.controller;

import com.example.demo.entity.Sales;
import com.example.demo.exception.ChargesNotFoundExceptions;
import com.example.demo.exception.SalesNotFoundException;
import com.example.demo.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("api/sales")
public class SalesController {
    private SalesService salesService;
    @Autowired
    public void setSalesService(SalesService salesService) { this.salesService= salesService; }

    @PostMapping(value = "/add/{warehouse_id}",  consumes = "application/json", produces = "application/json")
    public Sales addSales(@RequestBody Sales newSales, @PathVariable Long warehouse_id){
        return salesService.addSales(newSales, warehouse_id);
    }

    @DeleteMapping(value = "/delete/")
    public void deleteSales(@RequestParam("id") long id){
        try{
            salesService.deleteSales(id);
        }catch (ChargesNotFoundExceptions exceptions){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sales not found");
        }
    }

    @GetMapping("/hello")
    public ModelAndView getHello(){
        ModelAndView model = new ModelAndView();
        String message = "hello world!";
        model.addObject("message", message);
        model.setViewName("index");
        return model;
    }

    @GetMapping("/all")
    public ModelAndView getAllSales(){
        ModelAndView model = new ModelAndView();
        List<Sales> list = salesService.listSales();
        model.addObject("sales", list);
        model.setViewName("SalesView");
        return model;
    }

    @GetMapping("/id")
    public ModelAndView getSales(@RequestParam("id") long id){
        ModelAndView model = new ModelAndView();
        model.addObject("sales", salesService.findById(id));
        model.setViewName("SalesView");
        return model;
    }
    @GetMapping("/{id}")
    public ModelAndView getSales2(@PathVariable("id") long id){
        ModelAndView model = new ModelAndView();
        model.addObject("sales", salesService.findById(id));
        model.setViewName("SalesView");
        return model;
    }

    @GetMapping("sort/asc")
    public ResponseEntity<Sales> sortSalesASC(){
        try{
            return new ResponseEntity(salesService.sortASCById(), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales not found");
        }
    }

    @GetMapping("sort/desc")
    public ResponseEntity<Sales> sortSalesDESC(){
        try{
            return new ResponseEntity(salesService.sortDESCById(), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales not found");
        }
    }

    @PostMapping("update/amount")
    public void updateSalesAmount(@RequestParam("id") long id, @RequestParam int amount){
        salesService.updateAmount(id, amount);
    }

    @PostMapping("update/quantity")
    public void updateSalesQuantity(@RequestParam("id") long id, @RequestParam int quantity){
       salesService.updateQuantity(id, quantity);
    }

    @PostMapping("update/date")
    public void updateSalesDate(@RequestParam("id") long id, @RequestParam("sales_date") String tm){
        salesService.updateSalesDate(id, tm);
    }

    @GetMapping("/max_amount_sales_products")
    public List<Object> getMaxAmountSalesProduct() throws SalesNotFoundException {
        try {
            return salesService.maxAmountOfSalesProducts();
        } catch (SalesNotFoundException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales not found");
        }
    }

    @PostMapping("/update_in_store/quantity/{id}/{quantity}")
    public void updateQuantityInStore(@PathVariable long id, @PathVariable int quantity) {
        salesService.updateQuantityInStore(id, quantity);
    }

    @PostMapping("/update_in_store/amount/{id}/{quantity}")
    public void updateAmountInStore(@PathVariable long id, @PathVariable int quantity) {
        salesService.updateAmountInStore(id, quantity);
    }

    @GetMapping("/list_sales_product")
    public List<Object[]> listSalesProducts() throws SalesNotFoundException{
        return salesService.listSalesProducts();
    }
}
