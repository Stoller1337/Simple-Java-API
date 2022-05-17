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

import java.util.List;

@RestController
@RequestMapping("api/sales")
public class SalesController {
    private SalesService salesService;
    @Autowired
    public void setSalesService(SalesService salesService) { this.salesService= salesService; }

    @PostMapping(value = "/add/{warehouse_id}",  consumes = "application/json", produces = "application/json")
    public Sales addSales(@RequestBody Sales newSales, @PathVariable Long warehouse_id){ return salesService.addSales(newSales, warehouse_id); }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteSales(@PathVariable("id") long id){
        try{
            salesService.deleteSales(id);
        }catch (ChargesNotFoundExceptions exceptions){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sales not found");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sales>> getAllSales(){
        List<Sales> list = salesService.listSales();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSales(@PathVariable("id") long id){
        try{
            return new ResponseEntity<>(salesService.findById(id), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales not found");
        }
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

    @PutMapping("update/amount/{id}/{amount}")
    public ResponseEntity<Sales> updateSalesAmount(@PathVariable("id") long id, @PathVariable int amount){
        try{
            return new ResponseEntity(salesService.updateAmount(id, amount), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales not found");
        }
    }

    @PutMapping("/update/quantity/{id}")
    public ResponseEntity<Sales> updateSalesQuantity(@PathVariable("id") long id, int quantity){
        try{
            return new ResponseEntity(salesService.updateQuantity(id, quantity), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales not found");
        }
    }

    @PutMapping("/update/date/{id}")
    public ResponseEntity<Sales> updateSalesDate(@PathVariable("id") long id, String tm){
        try{
            return new ResponseEntity(salesService.updateSalesDate(id, tm), HttpStatus.OK);
        }catch(ChargesNotFoundExceptions exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sales not found");
        }
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
